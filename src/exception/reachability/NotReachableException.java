package exception.reachability;

import exception.factory.SemanticException;

public class NotReachableException extends SemanticException {
    public NotReachableException() {
    }

    public NotReachableException(String message) {
        super(message);
    }

    public NotReachableException(Throwable cause) {
        super(cause);
    }
}
