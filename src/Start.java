import model.expression.ArithExpression;
import model.expression.ValueExpression;
import model.expression.VarExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import view.View;

import java.util.HashMap;

public class Start {
    public static void main(String[] args) {
        HashMap<Integer, StatementInterface> programs = new HashMap<>();

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

        programs.put(1, ex1);
        programs.put(2, ex2);
        programs.put(3, ex3);
        View view = new View(programs);
        view.start();
    }

}
