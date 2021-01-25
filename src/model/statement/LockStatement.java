package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;
import model.value.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatement implements StatementInterface{
    private final String variableName;

    public LockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        var lockTable = state.getLockTable();
        var symbolTable = state.getSymbolTable();

        var foundIndex = symbolTable.get(variableName);
        if(foundIndex == null)
            throw new MyException("variable not found!");


        var value = lockTable.lookUpLock(((IntValue)foundIndex).getValue());

        if(value == -2)
            throw new MyException("There is no lock with this location!");

        if(value == -1) {

            lockTable.updateLock(((IntValue)foundIndex).getValue(), state.getId());
        }

        else
            state.getExecutionStack().push(this);

        return state;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "lock("+variableName+")";
    }
}
