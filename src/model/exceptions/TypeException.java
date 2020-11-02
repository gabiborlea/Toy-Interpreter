package model.exceptions;

public class TypeException extends RuntimeException {
    String message;

    public TypeException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
