package exception.time;

import exception.factory.SemanticException;
import node.statement.pins.PinIndexNode;
import node.statement.time.ResetNode;

public class NoTimerException extends SemanticException {

    public NoTimerException() {
    }

    public NoTimerException(String message) {
        super(message);
    }

    public NoTimerException(Throwable cause) {
        super(cause);
    }

    public NoTimerException(ResetNode node) {
        super("NoTimerException: The reset function is not written in a scope that contains a before/after function");
    }
}
