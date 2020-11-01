package model.expression;

import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.type.IntType;
import model.value.IntValue;
import model.value.ValueInterface;

public class ArithExpression implements ExpressionInterface{
    ExpressionInterface expression1, expression2;
    int operator;

    public ArithExpression(ExpressionInterface expression1, ExpressionInterface expression2, int operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws MyException {
        ValueInterface value1, value2;
        value1 = expression1.evaluate(table);

        if (value1.getType().equals(new IntType())){
            value2 = expression2.evaluate(table);

            if(value2.getType().equals(new IntType())){
                IntValue integer1 = (IntValue) value1;
                IntValue integer2 = (IntValue) value2;
                int number1 = integer1.getValue();
                int number2 = integer2.getValue();

                switch (operator){
                    case 1:
                        return new IntValue(number1+number2);

                    case 2:
                        return new IntValue(number1-number2);

                    case 3:
                        return new IntValue(number1*number2);

                    case 4:{
                        if(number2 == 0)
                            throw new MyException("Division by zero");

                        return new IntValue(number1/number2);
                    }
                    default:
                        throw new MyException("Wrong operator");

                }

            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");

    }

    @Override
    public String toString() {
        return switch (operator) {
            case 1 -> expression1 + "+" + expression2;
            case 2 -> expression2 + "-" + expression2;
            case 3 -> expression1 + "*" + expression2;
            case 4 -> expression1 + "/" + expression2;
            default -> expression1 + "wrong operator" + expression2;
        };
    }

}
