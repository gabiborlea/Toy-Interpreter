package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;
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

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeCondition = conditionExpression.typeCheck(typeEnv);
        if(!typeCondition.equals(new BoolType()))
            throw new TypeException("Condition in while loop is not of bool type");
        blockStatement.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "while (" + conditionExpression + ") do (" + blockStatement + ")";
    }
}
