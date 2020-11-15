package view.command;

import controller.Controller;
import model.exceptions.MyException;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStepsExecution();
        } catch (MyException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }
}
