package exception;

import exception.factory.SemanticException;

public class RecursionException extends SemanticException {
    public RecursionException() {}

    public RecursionException(String message) { super(message); }

    public RecursionException (String message, Throwable cause) { super(message, cause); }

    public RecursionException(Throwable cause) { super(cause); }

    public RecursionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
