package model.statement;

import model.ProgramState;
import model.exceptions.MyException;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws MyException;
}
