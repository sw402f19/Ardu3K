package exception.reachability;

import exception.factory.SemanticException;
import node.statement.termination.AbstractTerminalNode;
import node.statement.termination.ReturnNode;

public class NotReachableException extends SemanticException {
    public NotReachableException() {
    }

    public NotReachableException(String message) {
        super(message);
    }

    public NotReachableException(Throwable cause) {
        super(cause);
    }

    public NotReachableException(ReturnNode node) {
        super(node.getLine()+" NotReachableException: \""+node.toString()+"\" " +
                "not inside function body.");
    }
    public NotReachableException(AbstractTerminalNode node) {
        super(node.getLine()+" NotReachableException: \""+node.toString()+"\" " +
                "not inside control structure.");
    }
}
