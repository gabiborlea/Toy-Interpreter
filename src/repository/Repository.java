package repository;

import model.ProgramState;
import model.adt.List;
import model.adt.ListInterface;

public class Repository implements RepositoryInterface {
    ListInterface<ProgramState> programStates;
    int currentProgramState;

    public Repository(){
        programStates = new List<>();
        currentProgramState = 0;
    }

    @Override
    public void addProgramState(ProgramState program) {
        programStates.add(program);
        currentProgramState++;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programStates.get(currentProgramState - 1);
    }
}
