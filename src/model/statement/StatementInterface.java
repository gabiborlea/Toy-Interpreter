package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws MyException;

    DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException;
}
