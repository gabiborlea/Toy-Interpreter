package view.command;

import controller.Controller;
import model.exceptions.MyException;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStepsExecution();
        } catch (MyException exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }

    }
}
