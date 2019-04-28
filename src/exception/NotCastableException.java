package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class NotCastableException extends SemanticException {
    public NotCastableException() {
    }

    public NotCastableException(String message) {
        super(message);
    }

    public NotCastableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCastableException(Throwable cause) {
        super(cause);
    }

    public NotCastableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotCastableException(RootNode src, RootNode target) {
        super(src.getLine()+" NotCastableException: cannot cast "+target.toString()+
                " to "+src.toString());
    }
}
