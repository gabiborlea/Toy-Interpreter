package model.value;

import model.type.StringType;
import model.type.TypeInterface;

public class StringValue implements ValueInterface{
    String value;

    public StringValue(String value) { this.value = value; }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringValue && ((StringValue) another).value.equals(this.value);
    }

    @Override
    public String toString() {
        return '"'+value+'"';
    }
}
