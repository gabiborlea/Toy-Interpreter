package model.statement;

import model.ProgramState;
import model.exceptions.MyException;

public class NopStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return state;
    }
}
