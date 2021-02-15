package view.CLI.command;

import controller.Controller;
import model.ProgramState;
import model.adt.*;
import model.exceptions.MyException;
import model.statement.StatementInterface;
import repository.Repository;
import repository.RepositoryInterface;

public class RunExample extends Command {
    private final StatementInterface statement;
    private final String logFile;

    public RunExample(String key, String description, StatementInterface statement, String logFile) {
        super(key, description);
        this.statement = statement;
        this.logFile = logFile;
    }

    @Override
    public void execute() {
        try {
            statement.typeCheck(new Dictionary<>());
            ProgramState program = new ProgramState(new Stack<>(), new Stack<>(), new List<>(), new Dictionary<>(), new Heap<>(), new ProcTable(), statement);
            RepositoryInterface repository = new Repository(program, logFile);
            Controller controller = new Controller(repository);
            controller.allStepsExecution();
        } catch (MyException exception) {
//            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

    }
}
