package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.LockTableInterface;
import model.exceptions.MyException;
import model.exceptions.VariableDefinitionException;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatement implements StatementInterface{
    private final String variable;
    private static Lock lock = new ReentrantLock();

    public LockStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        LockTableInterface lockTable = state.getLockTable();
        if(!symbolTable.isDefined(variable))
            throw new VariableDefinitionException("variable " + variable + " is not defined");
        int foundIndex =((IntValue) symbolTable.get(variable)).getValue();

        if(!lockTable.isDefined(foundIndex))
            throw new VariableDefinitionException("variable " + variable + " is not a valid index in lock table");

        lock.lock();
        int thread = lockTable.get(foundIndex);
        if(thread == -1)
            lockTable.update(foundIndex, state.getId());

        else
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
        return "lock(" + variable + ")";
    }
}
