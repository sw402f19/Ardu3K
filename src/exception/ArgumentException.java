package exception;

import exception.factory.SemanticException;

public class ArgumentException extends SemanticException {

    public ArgumentException() {}

    public ArgumentException(String message) { super(message); }

    public ArgumentException(Throwable cause) { super(cause); }

}
