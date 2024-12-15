package exceptions;

public class OperationNotAllowed extends RuntimeException{
    public OperationNotAllowed(String message) {
        super(message);
    }
}
