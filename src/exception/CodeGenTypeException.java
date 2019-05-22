package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class CodeGenTypeException extends SemanticException {

    public CodeGenTypeException() {
    }

    public CodeGenTypeException(String message) {
        super(message);
    }

    public CodeGenTypeException(Throwable cause) {
        super(cause);
    }

    public CodeGenTypeException(RootNode node) {
        super(node.line + "CodeGenTypeException: Compiler not able to determine type from this :(");
    }
}
