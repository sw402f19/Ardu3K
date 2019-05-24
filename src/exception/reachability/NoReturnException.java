package exception.reachability;

import exception.factory.SemanticException;
import node.RootNode;
import node.scope.FunctionNode;

public class NoReturnException extends SemanticException {
    public NoReturnException() {
    }

    public NoReturnException(String message) {
        super(message);
    }

    public NoReturnException(Throwable cause) {
        super(cause);
    }
    public NoReturnException(FunctionNode node) {
        super(node.line+" NoReturnException: cannot evaluate return type of function "+node.getId().toString()+"("+node.parameterString()+")");
    }
}
