package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class IncompatibleTypeExpection extends AbstractException implements SemanticException {
    public IncompatibleTypeExpection() {
    }

    public IncompatibleTypeExpection(String message) {
        super(message);
    }

    public IncompatibleTypeExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleTypeExpection(Throwable cause) {
        super(cause);
    }

    public IncompatibleTypeExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String setErrorMessage(RootNode source, RootNode target) {
        return null;
    }
}
