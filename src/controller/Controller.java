package controller;

import model.ProgramState;
import model.adt.Dictionary;
import model.adt.List;
import model.adt.Stack;
import model.adt.StackInterface;
import model.exceptions.MyException;
import model.exceptions.StackException;
import model.statement.StatementInterface;
import repository.Repository;
import repository.RepositoryInterface;


public class Controller {
    RepositoryInterface repository;
    boolean showSteps;

    public Controller(RepositoryInterface repository, boolean showSteps){
        this.repository = repository;
        this.showSteps = showSteps;
    }

    public void addProgram(StatementInterface program){
        var newProgram = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), program);
        repository.addProgramState(newProgram);
    }

    public ProgramState oneStepExecution(ProgramState state) throws MyException {
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        if (executionStack.isEmpty())
            throw new StackException("Program State stack is empty");

        StatementInterface currentStatement = executionStack.pop();

        return currentStatement.execute(state);
    }

    public String allStepsExecution() throws MyException {
        ProgramState programState = repository.getCurrentProgramState();
        String separator = "------------------------\n";
        StringBuilder programStatesString = new StringBuilder(programState.toString());

        while (!programState.getExecutionStack().isEmpty()) {
            ProgramState executedProgramState = oneStepExecution(programState);
            programStatesString.append(separator);
            programStatesString.append(executedProgramState.toString());
        }
        return programStatesString.toString();
    }

    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }
}
