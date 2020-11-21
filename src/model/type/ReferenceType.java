package model.type;

import model.value.ReferenceValue;
import model.value.ValueInterface;

public class ReferenceType implements TypeInterface {
    TypeInterface inner;

    public ReferenceType(TypeInterface inner) {
        this.inner = inner;
    }

    TypeInterface getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof ReferenceType)
            return inner.equals(((ReferenceType) another).getInner());

        return false;

    }

    @Override
    public String toString() {
        return "Reference "+inner.toString();
    }

    @Override
    public ValueInterface defaultValue() {
        return new ReferenceValue(0,inner);
    }
}
