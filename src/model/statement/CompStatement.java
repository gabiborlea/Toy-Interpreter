package model.statement;

import model.ProgramState;
import model.adt.StackInterface;
import model.exceptions.MyException;

public class CompStatement implements StatementInterface{
    StatementInterface first;
    StatementInterface second;

    public CompStatement(StatementInterface first, StatementInterface second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        executionStack.push(second);
        executionStack.push(first);

        return state;
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + ";" + this.second.toString() + ")";
    }
}
