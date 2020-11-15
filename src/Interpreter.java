import controller.Controller;
import model.ProgramState;
import model.adt.Dictionary;
import model.adt.List;
import model.adt.Stack;
import model.expression.ArithExpression;
import model.expression.RelationalExpression;
import model.expression.ValueExpression;
import model.expression.VarExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import repository.Repository;
import repository.RepositoryInterface;
import view.TextMenu;
import view.command.ExitCommand;
import view.command.RunExample;

public class Interpreter {
    public static void main(String[] args) {

        StatementInterface ex1 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                    new CompStatement(
                            new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VarExpression("v"))));

        StatementInterface ex2 = new CompStatement(
                new VarDeclarationStatement("a",new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("b", new IntType()),
                        new CompStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithExpression(
                                                1, new ValueExpression(new IntValue(2)),
                                                new ArithExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)))
                                        )
                                ),
                                new CompStatement(
                                        new AssignStatement("b", new ArithExpression(1, new VarExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VarExpression("b"))
                                )
                        )
                )
        );

        StatementInterface ex3 = new CompStatement(
                new VarDeclarationStatement("a", new BoolType()),
                new CompStatement(
                        new VarDeclarationStatement("v", new IntType()),
                        new CompStatement(
                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompStatement(
                                        new IfStatement(new VarExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VarExpression("v"))
                                )
                        )
                )
        );

        StatementInterface ex4 = new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("input\\test.in"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CompStatement(
                                        new VarDeclarationStatement("varc", new IntType()),
                                        new CompStatement(
                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("varc")),
                                                        new CompStatement(
                                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                                new CompStatement(
                                                                        new PrintStatement(new VarExpression("varc")),
                                                                        new CloseRFileStatement(new VarExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                )
        );

        StatementInterface ex5 = new CompStatement(
                new VarDeclarationStatement("a", new IntType()),
                new CompStatement(
                        new AssignStatement("a", new ValueExpression(new IntValue(8))),
                        new CompStatement(
                            new VarDeclarationStatement("b", new IntType()),
                            new CompStatement(
                                new AssignStatement("b", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new VarDeclarationStatement("min", new IntType()),
                                        new CompStatement(
                                                new IfStatement(new RelationalExpression("<", new VarExpression("a"), new VarExpression("b")), new AssignStatement("min", new VarExpression("a")), new AssignStatement("min", new VarExpression("b"))),
                                                new PrintStatement(new VarExpression("min"))
                                        )
                                )
                            )
                        )
                )
        );

        ProgramState program1 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), ex1);
        RepositoryInterface repository1 = new Repository(program1, "logs\\log1.txt");
        Controller controller1 = new Controller(repository1, true);

        ProgramState program2 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), ex2);
        RepositoryInterface repository2 = new Repository(program2, "logs\\log2.txt");
        Controller controller2 = new Controller(repository2, true);

        ProgramState program3 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), ex3);
        RepositoryInterface repository3 = new Repository(program3, "logs\\log3.txt");
        Controller controller3 = new Controller(repository3, true);

        ProgramState program4 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), ex4);
        RepositoryInterface repository4 = new Repository(program4, "logs\\log4.txt");
        Controller controller4 = new Controller(repository4, true);

        ProgramState program5 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), ex5);
        RepositoryInterface repository5 = new Repository(program5, "logs\\log5.txt");
        Controller controller5 = new Controller(repository5, true);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));

        menu.start();
    }

}
