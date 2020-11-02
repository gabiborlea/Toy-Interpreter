package model.exceptions;

public class VariableDefinitionException extends RuntimeException {
    String message;

    public VariableDefinitionException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
