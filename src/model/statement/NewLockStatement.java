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

public class NewLockStatement implements StatementInterface {
    private final String variable;
    private static Lock lock = new ReentrantLock();

    public NewLockStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        LockTableInterface lockTable = state.getLockTable();


        lock.lock();
        int newPosition = lockTable.add(-1);
        if (!symbolTable.isDefined(variable))
            symbolTable.add(variable, new IntValue(newPosition));
        else
            symbolTable.update(variable, new IntValue((newPosition)));
        lock.unlock();

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLock(" + variable + ")";
    }
}
