package exception.time;

import exception.factory.SemanticException;
import node.statement.time.AbstractTimeStmtNode;

public class NoTimeTypeException  extends SemanticException {

    public NoTimeTypeException() {
    }

    public NoTimeTypeException(String message) {
        super(message);
    }

    public NoTimeTypeException(Throwable cause) {
        super(cause);
    }

    public NoTimeTypeException(AbstractTimeStmtNode node) {
        super("NoTimeTypeException: No valid time type in before/after function");
    }
}
