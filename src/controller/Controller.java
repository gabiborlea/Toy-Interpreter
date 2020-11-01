package controller;

import model.ProgramState;
import model.adt.StackInterface;
import model.exceptions.MyException;
import model.statement.StatementInterface;
import repository.RepositoryInterface;

public class Controller {
    RepositoryInterface repository;
    boolean showSteps;

    public Controller(RepositoryInterface repository, boolean showSteps){
        this.repository = repository;
        this.showSteps = showSteps;
    }

    public ProgramState oneStepExecution(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        if (stack.isEmpty())
            throw new MyException("Program State stack is empty");

        StatementInterface currentStatement = stack.pop();

        return currentStatement.execute(state);
    }

    public String allStepsExecution() throws MyException {
        ProgramState programState = repository.getCurrentProgramState();
        String separator = "------------------------\n";
        StringBuilder programStatesString = new StringBuilder(programState.toString() + "\n");

        while (!programState.getExecutionStack().isEmpty()) {
            ProgramState executedProgramState = oneStepExecution(programState);
            programStatesString.append(separator);
            programStatesString.append(executedProgramState.toString()).append("\n");

        }
        return programStatesString.toString();
    }

    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }
}
