package Exceptions;

public class NoBookingFoundException extends RuntimeException{

    public NoBookingFoundException(String message){
        throw new RuntimeException(message);
    }
}
