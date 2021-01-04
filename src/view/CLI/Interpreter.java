package view.CLI;

import utils.Programs;
import view.CLI.command.ExitCommand;
import view.CLI.command.RunExample;

import java.util.concurrent.atomic.AtomicInteger;

public class Interpreter {
    public static void main(String[] args) {


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        AtomicInteger idx = new AtomicInteger(1);
        Programs.getPrograms()
                .forEach(example-> menu.addCommand(new RunExample(idx.toString(), example.toString(), example, "logs\\log"+idx.getAndIncrement()+".txt")));

        menu.start();
    }

}
