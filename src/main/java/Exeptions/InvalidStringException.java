package Exeptions;

public class InvalidStringException extends Exception{

    public InvalidStringException(){
        super("String is invalid.");
    }

    public InvalidStringException(String message){
        super(message);
    }

}
