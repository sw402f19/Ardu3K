package exception.predicate;

import exception.factory.SemanticException;
import node.primary.AbstractPrimaryNode;
import node.statement.time.AbstractTimeStmtNode;

public class NeedsTimePredicateException extends SemanticException {
    public NeedsTimePredicateException() {
    }

    public NeedsTimePredicateException(String message) {
        super(message);
    }

    public NeedsTimePredicateException(Throwable cause) {
        super(cause);
    }

    public NeedsTimePredicateException(AbstractPrimaryNode node) {
        super(node.line+" NeedsTimePredicateException: got "+node.toString()+ " expected clock label");
    }
}
