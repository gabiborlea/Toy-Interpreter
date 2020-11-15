package repository;

import model.ProgramState;
import model.adt.List;
import model.adt.ListInterface;
import model.exceptions.InOutException;
import model.exceptions.MyException;
import model.value.StringValue;

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
    public ProgramState getCurrentProgramState() {
        return programStates.get(currentProgramState - 1);
    }

    @Override
    public void logProgramStateExecution() throws MyException{
        try {
            var logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(getCurrentProgramState().toString());
            logFile.close();
        } catch (IOException exception) {
            throw new InOutException(exception.getMessage());
        }
    }
}
