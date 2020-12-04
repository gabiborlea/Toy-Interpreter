package repository;

import model.ProgramState;
import model.adt.List;
import model.adt.ListInterface;
import model.exceptions.InOutException;
import model.exceptions.MyException;

import java.io.*;

public class Repository implements RepositoryInterface {
    ListInterface<ProgramState> programStates;
    int currentProgramState;
    String logFilePath;

    public Repository(ProgramState program, String logFilePath){
        programStates = new List<>();
        programStates.add(program);
        currentProgramState = 1;
        this.logFilePath = new File(logFilePath).getAbsolutePath();
        try {
            new FileOutputStream(this.logFilePath).close();
        } catch (IOException exception) {
            throw new InOutException(exception.getMessage());
        }
    }

    @Override
    public void addProgramState(ProgramState program) {
        programStates.add(program);
        currentProgramState++;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws MyException{
        try {
            var logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(programState.toString());
            logFile.close();
        } catch (IOException exception) {
            throw new InOutException(exception.getMessage());
        }
    }

    @Override
    public java.util.List<ProgramState> getProgramStatesList() {
        return programStates.getContent();
    }

    @Override
    public void setProgramStatesList(java.util.List<ProgramState> programStates) {
        this.programStates.setContent(programStates);
    }
}
