package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class NotCastableException extends SemanticException {
    public NotCastableException() {
    }

    public NotCastableException(String message) {
        super(message);
    }

    public NotCastableException(Throwable cause) {
        super(cause);
    }

    public NotCastableException(RootNode src, RootNode target) {
        super(src.getLine()+" NotCastableException: cannot cast "+target.toString()+
                " to "+src.toString());
    }
}
