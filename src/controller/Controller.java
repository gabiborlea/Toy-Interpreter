package controller;

import model.ProgramState;
import model.adt.*;
import model.exceptions.MyException;
import model.exceptions.StackException;
import model.statement.StatementInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import repository.RepositoryInterface;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


public class Controller {
    RepositoryInterface repository;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public void addProgram(StatementInterface program) {
        var newProgram = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), program);
        repository.addProgramState(newProgram);
    }

    public ProgramState oneStepExecution(ProgramState state) throws MyException {
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        if (executionStack.isEmpty())
            throw new StackException("Program State stack is empty");

        StatementInterface currentStatement = executionStack.pop();

        return currentStatement.execute(state);
    }

    public void allStepsExecution() throws MyException {
        ProgramState programState = repository.getCurrentProgramState();

        while (!programState.getExecutionStack().isEmpty()) {
            oneStepExecution(programState);

            programState.getMemoryHeap().setContent(safeGarbageCollector(
                    getAddressesSymbolTable(programState.getSymbolTable().getContent().values()),
                    getAddressesMemoryHeap(programState.getMemoryHeap().getContent().values()),
                    programState.getMemoryHeap().getContent()
            ));

//            programState.getMemoryHeap().setContent(unsafeGarbageCollector(
//                    getAddressesSymbolTable(programState.getSymbolTable().getContent().values()),
//                    programState.getMemoryHeap().getContent()
//            ));

            repository.logProgramStateExecution();

        }
    }

    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }

    private java.util.List<Integer> getAddressesSymbolTable(Collection<ValueInterface> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress())
                .collect(Collectors.toList());
    }

    private java.util.List<Integer> getAddressesMemoryHeap(Collection<ValueInterface> memoryHeapValues) {
        return memoryHeapValues.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress())
                .collect(Collectors.toList());
    }

    private Map<Integer, ValueInterface> safeGarbageCollector(java.util.List<Integer> symbolTableAddresses,
                                                              java.util.List<Integer> memoryHeappAddresses,
                                                              Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(e->symbolTableAddresses.contains(e.getKey()) || memoryHeappAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, ValueInterface> unsafeGarbageCollector(java.util.List<Integer> symbolTableAddresses,
                                                              Map<Integer, ValueInterface> heap) {
        return heap.entrySet().stream()
                .filter(e->symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



}
