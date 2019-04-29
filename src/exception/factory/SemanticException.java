package exception.factory;

import node.RootNode;

public abstract class SemanticException extends Exception {

    public SemanticException() {
    }

    public SemanticException(String message) {
        super(message);
    }

    public SemanticException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
