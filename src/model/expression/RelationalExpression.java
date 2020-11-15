package model.expression;

import model.adt.DictionaryInterface;
import model.exceptions.DivisonByZeroException;
import model.exceptions.MyException;
import model.exceptions.OperatorException;
import model.exceptions.TypeException;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

public class RelationalExpression implements ExpressionInterface{

    ExpressionInterface expression1, expression2;
    String operator;

    public RelationalExpression(String operator, ExpressionInterface expression1, ExpressionInterface expression2) {
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
                    case ">":
                        return new BoolValue(number1 > number2);

                    case ">=":
                        return new BoolValue(number1 >= number2);

                    case "<=":
                        return new BoolValue(number1 <= number2);

                    case "<":{
                        return new BoolValue(number1 < number2);
                    }
                    default:
                        throw new OperatorException("Wrong operator");

                }

            }
            else
                throw new TypeException("Second operand is not an integer");
        }
        else
            throw new TypeException("First operand is not an integer");
}
    @Override
    public String toString() {
        return switch (operator) {
            case ">" -> expression1 + ">" + expression2;
            case ">=" -> expression2 + ">=" + expression2;
            case "<=" -> expression1 + "<=" + expression2;
            case "<" -> expression1 + "<" + expression2;
            default -> expression1 + "wrong operator" + expression2;
        };
    }

}
