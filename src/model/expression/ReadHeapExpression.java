package model.expression;

import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.HeapException;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class ReadHeapExpression implements ExpressionInterface {
    private final ExpressionInterface expression;

    public ReadHeapExpression(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface value = expression.evaluate(table, heap);

        if (!(value.getType() instanceof ReferenceType))
            throw new TypeException(value + "is not of RefType");

        int address = ((ReferenceValue) value).getAddress();

        if (!heap.isDefined(address))
            throw new HeapException(value + " is not defined in the heap");

        return heap.get(address);
    }

    @Override
    public String toString() {
        return "readHeap(" + expression + ")";
    }
}
