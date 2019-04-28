package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class UndeclaredIdentifierException extends Exception implements SemanticException {
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

    @Override
    public String setErrorMessage(RootNode source, RootNode target) {
        return null;
    }
}
