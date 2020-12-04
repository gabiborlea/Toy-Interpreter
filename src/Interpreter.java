import controller.Controller;
import model.ProgramState;
import model.adt.Dictionary;
import model.adt.Heap;
import model.adt.List;
import model.adt.Stack;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
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
                new VarDeclarationStatement("a", new IntType()),
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

        StatementInterface ex6 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new PrintStatement(new VarExpression("a"))
                                        )
                                )
                        )
                )
        );

        StatementInterface ex7 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                                new PrintStatement(new ReadHeapExpression(new VarExpression("v"))),
                                                new PrintStatement(new ArithExpression(1, new ReadHeapExpression(new VarExpression("v")), new ValueExpression(new IntValue(5))))
                                        )
                                )
                        )
                )
        );

        StatementInterface ex8 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new PrintStatement(new ReadHeapExpression(new VarExpression("v"))),
                                new CompStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithExpression(1, new ReadHeapExpression(new VarExpression("v")), new ValueExpression(new IntValue(5))))

                                )
                        )
                )
        );

        StatementInterface ex9 = new CompStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new VarExpression("v")),
                                        new CompStatement(
                                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VarExpression("a"))))
                                        )
                                )
                        )
                )
        );

        StatementInterface ex10 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompStatement(
                                new WhileStatement(
                                        new RelationalExpression(">", new VarExpression("v"), new ValueExpression(new IntValue(0))),
                                        new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignStatement("v", new ArithExpression(2, new VarExpression("v"), new ValueExpression(new IntValue(1))))
                                        )),
                                new PrintStatement(new VarExpression("v"))

                        )
                )
        );

        StatementInterface ex11 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompStatement(
                                                new ForkStatement(
                                                        new CompStatement(
                                                                new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompStatement(
                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompStatement(
                                                                                new PrintStatement(new VarExpression("v")),
                                                                                new PrintStatement(new ReadHeapExpression(new VarExpression("a")))
                                                                        )
                                                                )

                                                        )
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VarExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState program1 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex1);
        RepositoryInterface repository1 = new Repository(program1, "logs\\log1.txt");
        Controller controller1 = new Controller(repository1);

        ProgramState program2 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex2);
        RepositoryInterface repository2 = new Repository(program2, "logs\\log2.txt");
        Controller controller2 = new Controller(repository2);

        ProgramState program3 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex3);
        RepositoryInterface repository3 = new Repository(program3, "logs\\log3.txt");
        Controller controller3 = new Controller(repository3);

        ProgramState program4 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex4);
        RepositoryInterface repository4 = new Repository(program4, "logs\\log4.txt");
        Controller controller4 = new Controller(repository4);

        ProgramState program5 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex5);
        RepositoryInterface repository5 = new Repository(program5, "logs\\log5.txt");
        Controller controller5 = new Controller(repository5);

        ProgramState program6 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex6);
        RepositoryInterface repository6 = new Repository(program6, "logs\\log6.txt");
        Controller controller6 = new Controller(repository6);

        ProgramState program7 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex7);
        RepositoryInterface repository7 = new Repository(program7, "logs\\log7.txt");
        Controller controller7 = new Controller(repository7);

        ProgramState program8 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex8);
        RepositoryInterface repository8 = new Repository(program8, "logs\\log8.txt");
        Controller controller8 = new Controller(repository8);

        ProgramState program9 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex9);
        RepositoryInterface repository9 = new Repository(program9, "logs\\log9.txt");
        Controller controller9 = new Controller(repository9);

        ProgramState program10 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex10);
        RepositoryInterface repository10 = new Repository(program10, "logs\\log10.txt");
        Controller controller10 = new Controller(repository10);

        ProgramState program11 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), ex11);
        RepositoryInterface repository11 = new Repository(program11, "logs\\log11.txt");
        Controller controller11 = new Controller(repository11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        menu.addCommand(new RunExample("11", ex11.toString(), controller11));

        menu.start();
    }

}
