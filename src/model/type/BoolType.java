package model.type;

public class BoolType {
    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }
}
