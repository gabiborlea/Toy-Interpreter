package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.type.TypeInterface;
import model.value.ValueInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException;
    TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException;
}
