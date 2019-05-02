package exception.reachability;

import exception.factory.SemanticException;
import node.RootNode;

public class NotReachableException extends SemanticException {
    public NotReachableException() {
    }

    public NotReachableException(String message) {
        super(message);
    }

    public NotReachableException(Throwable cause) {
        super(cause);
    }

    public NotReachableException(RootNode node) {
        super(node.getLine()+" NotReachableException: \""+node.toString()+"\" " +
                "not inside control structure.");
    }
}
