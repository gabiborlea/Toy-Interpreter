package model.type;

import model.value.IntValue;
import model.value.ValueInterface;

public class IntType implements TypeInterface{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public ValueInterface defaultValue() {
        return new IntValue(0);
    }
}
