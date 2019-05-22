package exception.predicate;

import exception.factory.SemanticException;
import node.statement.control.AbstractControlNode;

public class NeedsBooleanPredicateException extends SemanticException {
    public NeedsBooleanPredicateException() {
    }

    public NeedsBooleanPredicateException(String message) {
        super(message);
    }

    public NeedsBooleanPredicateException(Throwable cause) {
        super(cause);
    }

    public NeedsBooleanPredicateException(AbstractControlNode node) {
        super(node.getLine()+ " NeedsBooleanPredicateException: " +
                node.toString()+" expression cannot evaluate to boolean");
    }
}
