package model.exceptions;

public class MyException extends Exception{
    String message;

    public MyException(String message){
        this.message = message;
    }
}
