package exception;

public class IgnorableInvocationException extends Exception {
    public IgnorableInvocationException() {
    }

    public IgnorableInvocationException(String message) {
        super(message);
    }

    public IgnorableInvocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IgnorableInvocationException(Throwable cause) {
        super(cause);
    }

    public IgnorableInvocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
