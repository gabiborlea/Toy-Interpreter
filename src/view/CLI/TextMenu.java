package view.CLI;
import view.CLI.command.Command;

import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    private final HashMap<String, Command> commands;

    public void addCommand(Command command){
        commands.put(command.getKey(), command);
    }

    public TextMenu() {
        this.commands = new HashMap<>();
    }

    public void printMenu() {
        for (Command command : commands.values()){
            String line = String.format("%4s:%s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choose option>>>");
            String option = scanner.nextLine();
            Command command = commands.get(option);

            if (command == null){
                System.out.println("Invalid option!");
                continue;
            }
            command.execute();
        }

    }
}
