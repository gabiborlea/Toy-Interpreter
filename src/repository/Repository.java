package repository;

import model.ProgramState;

import java.util.ArrayList;

public class Repository implements RepositoryInterface {
    ArrayList<ProgramState> programStates;
    int currentProgramState;

    public Repository(){
        programStates = new ArrayList<>();
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
