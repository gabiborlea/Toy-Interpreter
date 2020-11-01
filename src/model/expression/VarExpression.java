package model.expression;

import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.value.ValueInterface;

public class VarExpression implements ExpressionInterface{
    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws MyException {
        return table.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
