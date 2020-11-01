package model.value;

import model.type.BoolType;
import model.type.TypeInterface;

public class BoolValue implements ValueInterface{
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
