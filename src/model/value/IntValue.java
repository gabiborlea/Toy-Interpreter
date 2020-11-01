package model.value;

import model.type.IntType;
import model.type.TypeInterface;

public class IntValue implements ValueInterface{
    int value;

    public IntValue(int value){
        this.value = value;
    }

    @Override
    public TypeInterface getType() {
        return new IntType();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
