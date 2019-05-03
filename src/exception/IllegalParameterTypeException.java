package exception;

import exception.factory.SemanticException;

public class IllegalParameterTypeException extends SemanticException {
    public IllegalParameterTypeException() {}

    public IllegalParameterTypeException(String message) { super(message); }

    public IllegalParameterTypeException(Throwable cause) { super(cause); }

}