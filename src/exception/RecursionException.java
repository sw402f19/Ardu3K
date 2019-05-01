package exception;

import exception.factory.SemanticException;

public class RecursionException extends SemanticException {
    public RecursionException() {}

    public RecursionException(String message) { super(message); }

    public RecursionException(Throwable cause) { super(cause); }
}
