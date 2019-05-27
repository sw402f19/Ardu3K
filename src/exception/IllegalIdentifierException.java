package exception;

import exception.factory.SemanticException;
import node.primary.IdentifierNode;

public class IllegalIdentifierException extends SemanticException {
    public IllegalIdentifierException() {
    }

    public IllegalIdentifierException(String message) {
        super(message);
    }

    public IllegalIdentifierException(Throwable cause) {
        super(cause);
    }
    public IllegalIdentifierException(IdentifierNode node) {
        super(node.getLine()+" IllegalIdentifierNode: identifier name "+node.toString()+" not legal.");
    }
}
