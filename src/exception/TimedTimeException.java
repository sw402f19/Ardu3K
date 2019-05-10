package exception;

import exception.factory.SemanticException;
import node.statement.TimedNode;
import node.statement.pins.PinIndexNode;

public class TimedTimeException extends SemanticException {

    public TimedTimeException() {
    }

    public TimedTimeException(String message) {
        super(message);
    }

    public TimedTimeException(Throwable cause) {
        super(cause);
    }

    public TimedTimeException(TimedNode node) {
        super(node.line + node.getWaitTime() + "is invalid wait time!");
    }
}