package repository;

import model.ProgramState;

public interface RepositoryInterface {
    void addProgramState(ProgramState program);
    ProgramState getCurrentProgramState();
}
