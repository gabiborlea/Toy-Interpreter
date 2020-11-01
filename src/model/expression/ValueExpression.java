package model.expression;

import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface{
    ValueInterface value;

    public ValueExpression(ValueInterface value){
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws MyException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
