package exception.factory;

public class NoProductException extends RuntimeException {

    public NoProductException() {
    }

    public NoProductException(String message) {
        super("NoProductException: "+message);

    }

    public NoProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProductException(Throwable cause) {
        super(cause);
    }

    public NoProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
