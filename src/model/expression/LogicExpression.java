package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.exceptions.OperatorException;
import model.exceptions.TypeException;
import model.type.BoolType;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;

public class LogicExpression implements ExpressionInterface{
    ExpressionInterface expression1, expression2;
    int operator;

    public LogicExpression(ExpressionInterface expression1, ExpressionInterface expression2, int operator){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface value1, value2;
        value1 = expression1.evaluate(table, heap);

        if (value1.getType().equals(new BoolType())){
            value2 = expression2.evaluate(table, heap);

            if(value2.getType().equals(new BoolType())){
                BoolValue boolean1 = (BoolValue) value1;
                BoolValue boolean2 = (BoolValue) value2;
                boolean operand1 = boolean1.getValue();
                boolean operand2 = boolean2.getValue();

                return switch (operator) {
                    case 1 -> new BoolValue(operand1 && operand2);
                    case 2 -> new BoolValue(operand1 || operand2);
                    default -> throw new OperatorException("Wrong operator");
                };

            }
            else
                throw new TypeException("Second operand is not a boolean");
        }
        else
            throw new TypeException("First operand is not a boolean");
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);

        if (!type1.equals(new BoolType()))
            throw new TypeException("first operand is not a boolean");

        if (!type2.equals(new BoolType()))
            throw new TypeException("second operand is not a boolean");

        return new BoolType();
    }

    @Override
    public String toString() {
        return switch (operator) {
            case 1 -> expression1 + "&&" + expression2;
            case 2 -> expression2 + "||" + expression2;
            default -> expression1 + "wrong operator" + expression2;
        };
    }
}
