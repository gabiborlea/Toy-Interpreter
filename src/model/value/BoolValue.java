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

    @Override
    public ValueInterface copy() {
        return new BoolValue(value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object another){
        return another instanceof BoolValue && ((BoolValue) another).value == (this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
