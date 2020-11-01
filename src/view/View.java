package view;

import controller.Controller;
import model.ProgramState;
import model.adt.List;
import model.exceptions.MyException;
import model.statement.StatementInterface;
import repository.Repository;
import repository.RepositoryInterface;

import java.util.HashMap;
import java.util.Scanner;

public class View {
    HashMap<Integer, StatementInterface> programs;

    public View(HashMap<Integer, StatementInterface> programs) {
        this.programs = programs;
    }

    public void printMenu() {
        System.out.println("Choose on option");
        System.out.println("1.Execute Program1");
        System.out.println("2.Execute Program2");
        System.out.println("3.Execute Program3");
        System.out.println("4.Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int ok = 1;
        while (ok == 1) {
            printMenu();
            System.out.print(">>>");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    try {
                        Repository repository = new Repository();
                        Controller controller = new Controller(repository, true);
                        controller.addProgram(programs.get(1));
                        System.out.println(controller.allStepsExecution());
                    } catch (MyException exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        Repository repository = new Repository();
                        Controller controller = new Controller(repository, true);
                        controller.addProgram(programs.get(2));
                        System.out.println(controller.allStepsExecution());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        Repository repository = new Repository();
                        Controller controller = new Controller(repository, true);
                        controller.addProgram(programs.get(3));
                        System.out.println(controller.allStepsExecution());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                case 4 -> ok = 0;
                default -> System.out.println("Wrong option");
            }
        }
    }
}
