package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;
import model.value.IntValue;

public class UnlockStatement implements StatementInterface{
    private final String variableName;

    public UnlockStatement(String variableName) {
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
            return state;

        if(value == -1)
            return state;

        else
            lockTable.updateLock(((IntValue)foundIndex).getValue(), -1);

        return state;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "unlock("+variableName+")";
    }
}
