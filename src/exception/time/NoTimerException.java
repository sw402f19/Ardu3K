package exception.time;

import exception.factory.SemanticException;
import node.statement.pins.PinIndexNode;
import node.statement.time.ResetNode;
import node.statement.time.ResetSpecificNode;

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
        super(node.line + " NoTimerException: The reset function is not written in a scope that contains a before/after function");
    }

    public NoTimerException(ResetSpecificNode node){
        super(node.line + " NoTimerException: The timer is not found!");
    }
}
