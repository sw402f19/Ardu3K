package exception;

import exception.factory.SemanticException;
import node.statement.control.AbstractControlNode;
import node.statement.control.WhileNode;

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
