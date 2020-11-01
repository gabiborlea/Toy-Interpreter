package model.expression;

import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.value.ValueInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws MyException;
}
