package exception;

public class UndeclaredIdentifierException extends Exception {
    public UndeclaredIdentifierException() {
    }

    public UndeclaredIdentifierException(String message) {
        super(message);
    }

    public UndeclaredIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndeclaredIdentifierException(Throwable cause) {
        super(cause);
    }

    public UndeclaredIdentifierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
