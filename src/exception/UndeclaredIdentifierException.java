package exception;

import exception.factory.SemanticException;
import node.RootNode;
import node.primary.IdentifierNode;

public class UndeclaredIdentifierException extends SemanticException {
    public UndeclaredIdentifierException() {
    }

    public UndeclaredIdentifierException(String message) {
        super(message);
    }
    public UndeclaredIdentifierException(IdentifierNode node) {
        super(node.getLine()+" UndeclaredIdentifierException - identifier \""
                +node.toString()+"\" not declared");
    }

    public UndeclaredIdentifierException(Throwable cause) {
        super(cause);
    }
}
