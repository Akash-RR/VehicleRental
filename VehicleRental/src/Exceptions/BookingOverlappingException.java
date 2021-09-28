package Exceptions;

public class BookingOverlappingException extends RuntimeException{

    public BookingOverlappingException(String message){
        throw new RuntimeException(message);
    }
}
