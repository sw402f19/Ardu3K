package exception.predicate;

import exception.factory.SemanticException;
import node.RootNode;
import node.statement.control.AbstractControlNode;

public class NeedsNumeralPredicateException extends SemanticException {
    public NeedsNumeralPredicateException() {
    }

    public NeedsNumeralPredicateException(String message) {
        super(message);
    }

    public NeedsNumeralPredicateException(Throwable cause) {

    }
    public NeedsNumeralPredicateException(AbstractControlNode node, RootNode type) {
        super(node.line+" NeedsNumeralPredicateException: "+node.toString()+" cannot evaluate to a range\n"
        +"expected Integer or Float, got: "+type.toString());
    }
}
