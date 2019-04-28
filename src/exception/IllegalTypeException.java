package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class IllegalTypeException extends AbstractException implements SemanticException {
    public IllegalTypeException() {
    }

    public IllegalTypeException(String message) {

        super(message);
    }

    public IllegalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTypeException(Throwable cause) {
        super(cause);
    }

    public IllegalTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String setErrorMessage(RootNode source, RootNode target) {
        return null;
    }
}
