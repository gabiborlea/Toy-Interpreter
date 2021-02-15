package utils;

import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programs {
    public static List<StatementInterface> getPrograms() {
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

        StatementInterface ex12 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompStatement(
                                new WhileStatement(
                                        new ValueExpression(new IntValue(40)),
                                        new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignStatement("v", new ArithExpression(2, new VarExpression("v"), new ValueExpression(new IntValue(1))))
                                        )),
                                new PrintStatement(new VarExpression("v"))

                        )
                )
        );

        StatementInterface ex13 = new CompStatement(
                new NewProcedureStatement("sum", Arrays.asList("a", "b"), new CompStatement(
                        new VarDeclarationStatement("v", new IntType()),
                        new CompStatement(
                                new AssignStatement("v", new ArithExpression(1, new VarExpression("a"), new VarExpression("b"))),
                                new PrintStatement(new VarExpression("v"))
                        )
                )
                ),
                new CompStatement(
                        new NewProcedureStatement("product", Arrays.asList("a", "b"), new CompStatement(
                                new VarDeclarationStatement("v", new IntType()),
                                new CompStatement(
                                        new AssignStatement("v", new ArithExpression(3, new VarExpression("a"), new VarExpression("b"))),
                                        new PrintStatement(new VarExpression("v"))
                                )
                        )
                        ),
                        new CompStatement(
                                new VarDeclarationStatement("v", new IntType()),
                                new CompStatement(
                                        new VarDeclarationStatement("w", new IntType()),
                                        new CompStatement(
                                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                                new CompStatement(
                                                        new AssignStatement("w", new ValueExpression(new IntValue(5))),
                                                        new CompStatement(
                                                                new CallProcedureStatement("sum", Arrays.asList(
                                                                        new ArithExpression(3, new VarExpression("v"), new ValueExpression(new IntValue(10))), new VarExpression("w"))),
                                                                new CompStatement(
                                                                        new PrintStatement(new VarExpression("v")),
                                                                        new CompStatement(
                                                                                new ForkStatement(
                                                                                        new CallProcedureStatement("product", Arrays.asList(new VarExpression("v"), new VarExpression("w")))
                                                                                ),
                                                                                new ForkStatement(
                                                                                        new CallProcedureStatement("sum", Arrays.asList(new VarExpression("v"), new VarExpression("w")))
                                                                                )
                                                                        )
                                                                )


                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        StatementInterface ex14 = new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(0))),
                        new CompStatement(
                                new WhileStatement(
                                        new RelationalExpression("<", new VarExpression("v"), new ValueExpression(new IntValue(3))),
                                        new CompStatement(
                                                new ForkStatement(
                                                        new PrintStatement(new VarExpression("v"))
                                                ),
                                                new AssignStatement("v",
                                                        new ArithExpression(1, new VarExpression("v"), new ValueExpression(new IntValue(1))))
                                        )
                                ),
                                new CompStatement(
                                        new SleepStatement(new IntValue(5)),
                                        new PrintStatement(new ArithExpression(3, new VarExpression("v"), new ValueExpression(new IntValue(10))))
                                )
                        )
                )
        );
        return new ArrayList<>(List.of(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14));
    }
}
