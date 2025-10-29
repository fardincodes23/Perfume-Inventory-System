package ca.hccis.perfume.exception;

public class BusPassException extends RuntimeException {

    public BusPassException(String message) {
        super(message);
    }

    public BusPassException(){
        super("Bus Pass Exception");
    }
}
