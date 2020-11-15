package repository;

import model.ProgramState;
import model.exceptions.MyException;

public interface RepositoryInterface {
    void addProgramState(ProgramState program);
    ProgramState getCurrentProgramState();
    void logProgramStateExecution() throws MyException;
}
