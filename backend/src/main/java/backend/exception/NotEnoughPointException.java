package backend.exception;

public class NotEnoughPointException extends RuntimeException{
    public NotEnoughPointException() {
        super();
    }

    public NotEnoughPointException(String message) {
        super(message);
    }

    public NotEnoughPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPointException(Throwable cause) {
        super(cause);
    }
}
