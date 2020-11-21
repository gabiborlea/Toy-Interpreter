package model.statement;

import model.ProgramState;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.ValueInterface;

public class WhileStatement implements StatementInterface{
    private final ExpressionInterface conditionExpression;
    private final StatementInterface blockStatement;

    public WhileStatement(ExpressionInterface expression, StatementInterface blockStatement) {
        this.conditionExpression = expression;
        this.blockStatement = blockStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface conditionValue = conditionExpression.evaluate(state.getSymbolTable(), state.getMemoryHeap());

        if (!(conditionValue.getType() instanceof BoolType))
            throw new TypeException(conditionExpression + "is not of BoolType");

        if (((BoolValue) conditionValue).getValue()) {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(blockStatement);
        }

        return state;
    }

    @Override
    public String toString() {
        return "while (" + conditionExpression + ") do (" + blockStatement + ")";
    }
}
