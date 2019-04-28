package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class IncompatibleTypeExpection extends SemanticException{
    public IncompatibleTypeExpection() {
    }

    public IncompatibleTypeExpection(String message) {
        super(message);
    }

    public IncompatibleTypeExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleTypeExpection(Throwable cause) {
        super(cause);
    }

    public IncompatibleTypeExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncompatibleTypeExpection(RootNode src, RootNode target) {
        super(src.getLine()+" IncompatibleTypeException: got "+target.toString()+" " +
                "expected "+src.toString());
    }
}
