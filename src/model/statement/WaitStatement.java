package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.expression.ValueExpression;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

public class WaitStatement implements StatementInterface{
    public final ExpressionInterface expression;

    public WaitStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface value = expression.evaluate(state.getSymbolTable(), state.getMemoryHeap());
        if(!value.getType().equals(new IntType()))
            throw new TypeException(expression + "is not of int type");

        int number = ((IntValue) value).getValue();
        if(number > 0)
            state.getExecutionStack().push(
                    new CompStatement(
                            new PrintStatement(expression),
                            new WaitStatement(new ValueExpression(new IntValue(number-1)))
                    )
        );
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "wait(" + expression + ")";
    }
}
