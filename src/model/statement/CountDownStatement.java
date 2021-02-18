package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.VariableDefinitionException;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements StatementInterface {
    private final String variable;
    private final static Lock lock = new ReentrantLock();

    public CountDownStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        if (!state.getSymbolTable().isDefined(variable))
            throw new VariableDefinitionException(variable + " is not defined");
        ValueInterface value = state.getSymbolTable().get(variable);
        int foundIndex = ((IntValue) value).getValue();
        if (!state.getLatchTable().isDefined(foundIndex))
            throw new VariableDefinitionException(foundIndex + " is not an index");
        int number = state.getLatchTable().get(foundIndex);
        lock.lock();
        if (number > 0) {
            state.getLatchTable().update(foundIndex, number - 1);
            state.getOutput().add(new IntValue(state.getId()));
        }

        lock.unlock();
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "countDown(" + variable + ")";
    }
}
