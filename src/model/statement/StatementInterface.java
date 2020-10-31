package model.statement;

import model.PrgState;
import model.exceptions.MyException;

public interface StatementInterface {
    PrgState execute(PrgState state) throws MyException;
}
