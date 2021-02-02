package backend.exception;

public class DuplicatedFollowingException extends RuntimeException{

    public DuplicatedFollowingException() {
        super();
    }

    public DuplicatedFollowingException(String message) {
        super(message);
    }

    public DuplicatedFollowingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedFollowingException(Throwable cause) {
        super(cause);
    }
}
