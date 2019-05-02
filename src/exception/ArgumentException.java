package exception;

import exception.factory.SemanticException;

public class ArgumentException extends SemanticException {

    public ArgumentException() {}

    public ArgumentException(String message) { super(message); }

    public ArgumentException(String message, Throwable cause) { super(message, cause); }

    public ArgumentException(Throwable cause) { super(cause); }

    public ArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
