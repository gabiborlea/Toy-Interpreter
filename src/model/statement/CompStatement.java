package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.StackInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;

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

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + ";" + this.second.toString() + ")";
    }
}
