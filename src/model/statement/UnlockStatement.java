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

public class UnlockStatement implements StatementInterface {
    public final String variable;
    public static Lock lock = new ReentrantLock();

    public UnlockStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        LockTableInterface lockTable = state.getLockTable();
        if (!symbolTable.isDefined(variable))
            throw new VariableDefinitionException("variable " + variable + " is not defined");
        int foundIndex = ((IntValue) symbolTable.get(variable)).getValue();
        lock.lock();
        if (!lockTable.isDefined(foundIndex))
            return null;

        else if (lockTable.get(foundIndex) == state.getId())
            lockTable.update(foundIndex, -1);

        lock.unlock();

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "unlock(" + variable + ")";
    }
}
