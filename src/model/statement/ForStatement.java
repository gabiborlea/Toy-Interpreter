package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.expression.ExpressionInterface;
import model.expression.RelationalExpression;
import model.expression.VarExpression;
import model.type.TypeInterface;

public class ForStatement implements StatementInterface {
    private final String variable;
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private final ExpressionInterface expression3;
    private final StatementInterface statement;

    public ForStatement(String variable, ExpressionInterface expression1, ExpressionInterface expression2, ExpressionInterface expression3, StatementInterface statement) {
        this.variable = variable;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StatementInterface newStatement = new CompStatement(
                new AssignStatement(variable, expression1),
                new WhileStatement(
                        new RelationalExpression("<", new VarExpression(variable), expression2),
                        new CompStatement(
                                statement,
                                new AssignStatement(variable, expression3)
                        )
                )
        );
        state.getExecutionStack().push(newStatement);
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "for(" + variable + "=" + expression1 + ";" + variable + "<" + expression2 + ";" + variable + "=" + expression3 + ") " + statement;
    }
}
