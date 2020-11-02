package model.exceptions;

public class DivisonByZeroException extends RuntimeException {
    String message;

    public DivisonByZeroException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
