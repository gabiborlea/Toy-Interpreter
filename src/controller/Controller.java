package controller;

import model.ProgramState;
import model.exceptions.MyException;
import model.exceptions.RunTimeException;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import repository.RepositoryInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    RepositoryInterface repository;
    ExecutorService executor;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public java.util.List<ProgramState> removeCompletedPrograms(java.util.List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());

    }

    public void oneStepForAllPrograms(java.util.List<ProgramState> programStatesList) {
        programStatesList.forEach(program -> repository.logProgramStateExecution(program));
        java.util.List<Callable<ProgramState>> callList = programStatesList.stream()
                .map((ProgramState program) -> (Callable<ProgramState>) (program::oneStepExecution))
                .collect(Collectors.toList());

        java.util.List<ProgramState> newProgramStatesList;
        try {
            newProgramStatesList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception exception) {
                            throw new RunTimeException(exception.getMessage());
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException exception) {
            throw new RunTimeException(exception.getMessage());
        }

        programStatesList.addAll(newProgramStatesList);
        programStatesList.forEach(program -> repository.logProgramStateExecution(program));
        repository.setProgramStatesList(programStatesList);
    }

    public void allStepsExecution() throws MyException {
        executor = Executors.newFixedThreadPool(2);

        java.util.List<ProgramState> programStatesList = removeCompletedPrograms(repository.getProgramStatesList());

        while (programStatesList.size() > 0) {

            var memoryHeap = programStatesList.get(0).getMemoryHeap();
            memoryHeap.setContent(safeGarbageCollector(
                    getAddressesFromSymbolTables(programStatesList),
                    getAddressesMemoryHeap(memoryHeap.getContent().values()),
                    memoryHeap.getContent()
                    )
            );
            oneStepForAllPrograms(programStatesList);
            programStatesList = removeCompletedPrograms(repository.getProgramStatesList());
        }
        executor.shutdown();
        repository.setProgramStatesList(programStatesList);
    }

    public void oneStepExecution() throws MyException {
        executor = Executors.newFixedThreadPool(2);

        java.util.List<ProgramState> programStatesList = removeCompletedPrograms(repository.getProgramStatesList());
        repository.setProgramStatesList(programStatesList);
        if (programStatesList.size() == 0) {
            executor.shutdownNow();
            throw new RunTimeException("Program is finished");
        }
        var memoryHeap = programStatesList.get(0).getMemoryHeap();
        memoryHeap.setContent(safeGarbageCollector(
                getAddressesFromSymbolTables(programStatesList),
                getAddressesMemoryHeap(memoryHeap.getContent().values()),
                memoryHeap.getContent()
                )
        );
        oneStepForAllPrograms(programStatesList);
        programStatesList = removeCompletedPrograms(repository.getProgramStatesList());
        executor.shutdown();
        repository.setProgramStatesList(programStatesList);
    }

    public java.util.List<ProgramState> getProgramStatesList() {
        return repository.getProgramStatesList();
    }

    public java.util.List<Integer> getAddressesSymbolTable(Collection<ValueInterface> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress())
                .collect(Collectors.toList());
    }

    public java.util.List<Integer> getAddressesMemoryHeap(Collection<ValueInterface> memoryHeapValues) {
        return memoryHeapValues.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress())
                .collect(Collectors.toList());
    }

    public java.util.List<Integer> getAddressesFromSymbolTables(java.util.List<ProgramState> programStatesList) {

        return programStatesList.stream()
                .flatMap(programState -> getAddressesSymbolTable(programState.getSymbolTable().getContent().values()).stream())
                .distinct().collect(Collectors.toCollection(ArrayList::new));

    }

    public Map<Integer, ValueInterface> safeGarbageCollector(java.util.List<Integer> symbolTableAddresses,
                                                             java.util.List<Integer> memoryHeapAddresses,
                                                             Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()) || memoryHeapAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, ValueInterface> unsafeGarbageCollector(java.util.List<Integer> symbolTableAddresses,
                                                                Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
