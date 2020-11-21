package model.value;

import model.type.ReferenceType;
import model.type.TypeInterface;

public class ReferenceValue implements ValueInterface {
    int address;
    TypeInterface locationType;

    public ReferenceValue(int address, TypeInterface locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public TypeInterface getLocationType() {
        return locationType;
    }

    @Override
    public TypeInterface getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}
