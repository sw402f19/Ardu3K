package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class InvalidDASTTopNode extends SemanticException {

    public InvalidDASTTopNode() {
    }

    public InvalidDASTTopNode(String message) {
        super(message);
    }

    public InvalidDASTTopNode(Throwable cause) {
        super(cause);
    }

    public InvalidDASTTopNode(RootNode node) {
        super("INVALID TOP NODE FOR CODE GEN! Expected ProgramNode got " + node.getClass().getSimpleName());
    }
}