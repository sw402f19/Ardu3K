package exception;

import exception.factory.SemanticException;

public class IllegalTypeException extends SemanticException {
    public IllegalTypeException() {
    }

    public IllegalTypeException(String message) {

        super(message);
    }
    public IllegalTypeException(Throwable cause) {
        super(cause);
    }
}
