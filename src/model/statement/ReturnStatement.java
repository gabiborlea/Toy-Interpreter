package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.StackException;
import model.type.TypeInterface;

public class ReturnStatement implements StatementInterface{
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if(state.getSymbolTableStack().isEmpty())
            throw new StackException("there is no procedure to return from");

        state.getSymbolTableStack().pop();
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "return";
    }
}
