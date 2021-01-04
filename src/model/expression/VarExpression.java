package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;
import model.value.ValueInterface;

public class VarExpression implements ExpressionInterface{
    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException {
        return table.get(id);
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
