package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

public class MulExpression implements ExpressionInterface {
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;

    public MulExpression(ExpressionInterface expression1, ExpressionInterface expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface value1, value2;
        value1 = expression1.evaluate(table, heap);
        if (!value1.getType().equals(new IntType()))
            throw new TypeException(expression1 + " is not evaluated to int");
        value2 = expression2.evaluate(table, heap);

        if (!value2.getType().equals(new IntType()))
            throw new TypeException(expression2 + " is not evaluated to int");

        IntValue integer1 = (IntValue) value1;
        IntValue integer2 = (IntValue) value2;
        int number1 = integer1.getValue();
        int number2 = integer2.getValue();
        return new IntValue((number1 * number2) - (number1 + number2));
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return new IntType();
    }

    @Override
    public String toString() {
        return "mul(" + expression1 + "," + expression2 + ")";
    }
}
