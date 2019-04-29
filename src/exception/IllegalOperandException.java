package exception;

import exception.factory.SemanticException;
import node.RootNode;

public class IllegalOperandException extends SemanticException {

    public IllegalOperandException() {
    }
    public IllegalOperandException(String message) {
        super(message);
    }

    public IllegalOperandException(Throwable cause) {
        super(cause);
    }


    public IllegalOperandException(RootNode operator, RootNode operand) {
        super(operator.getLine()+" IllegalOperandException: cannot apply \""
                +operand.toString()+"\" to operator \""+operator.toString()+"\"");
    }

}
