package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class TimedTimeException extends SemanticException {

    public TimedTimeException() {
    }

    public TimedTimeException(String message) {
        super(message);
    }

    public TimedTimeException(Throwable cause) {
        super(cause);
    }

    public TimedTimeException(RootNode node) {
        super(node.line  + "is invalid wait time!");
    }
}