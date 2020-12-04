package repository;

import model.ProgramState;
import model.exceptions.MyException;


public interface RepositoryInterface {
    void addProgramState(ProgramState program);
    void logProgramStateExecution(ProgramState programState) throws MyException;
    java.util.List<ProgramState> getProgramStatesList();
    void setProgramStatesList(java.util.List<ProgramState> programStates);
}
