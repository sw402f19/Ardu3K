package exception.type;

import exception.factory.SemanticException;
import node.RootNode;

public class IncompatibleTypeExpection extends SemanticException{
    public IncompatibleTypeExpection() {
    }

    public IncompatibleTypeExpection(String message) {
        super(message);
    }

    public IncompatibleTypeExpection(Throwable cause) {
        super(cause);
    }

    public IncompatibleTypeExpection(RootNode src, RootNode target) {
        super(target.getLine()+" IncompatibleTypeException: expected "+src.toString()+"" +
                ", got "+target.toString());
    }
}
