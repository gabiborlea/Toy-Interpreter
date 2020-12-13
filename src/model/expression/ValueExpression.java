package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;
import model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface{
    ValueInterface value;

    public ValueExpression(ValueInterface value){
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException {
        return value;
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
