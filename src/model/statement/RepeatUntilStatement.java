package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.expression.ValueExpression;
import model.type.BoolType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;

public class RepeatUntilStatement implements StatementInterface{
    private final StatementInterface statement;
    private final ExpressionInterface expression1;

    public RepeatUntilStatement(StatementInterface statement, ExpressionInterface expression1) {
        this.statement = statement;
        this.expression1 = expression1;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface value = expression1.evaluate(state.getSymbolTable(), state.getMemoryHeap());
        if(!value.getType().equals(new BoolType()))
            throw new TypeException(expression1 + " is not of bool type");

        StatementInterface newStatement = new CompStatement(
                statement,
                new WhileStatement(expression1, statement, false)
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
        return "repeat " + statement + " until " + expression1;
    }
}
