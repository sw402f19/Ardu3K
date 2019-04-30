package exception;

import exception.factory.SemanticException;

public class IllegalParameterTypeException extends SemanticException {
    public IllegalParameterTypeException() {}

    public IllegalParameterTypeException(String message) { super(message); }

    public IllegalParameterTypeException (String message, Throwable cause) { super(message, cause); }

    public IllegalParameterTypeException(Throwable cause) { super(cause); }

    public IllegalParameterTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}