package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.VariableDefinitionException;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStatement implements StatementInterface{
    private final String variable;
    private final static Lock lock = new ReentrantLock();

    public AwaitStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        if(!state.getSymbolTable().isDefined(variable))
            throw new VariableDefinitionException(variable + " is not defined");
        ValueInterface value = state.getSymbolTable().get(variable);
        int foundIndex = ((IntValue) value).getValue();
        if(!state.getLatchTable().isDefined(foundIndex))
            throw new VariableDefinitionException(foundIndex + " is not an index");
        lock.lock();
        if(state.getLatchTable().get(foundIndex) != 0)
            state.getExecutionStack().push(this);
        lock.unlock();
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "await(" + variable + ")";
    }
}
