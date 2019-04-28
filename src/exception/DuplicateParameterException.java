package exception;

import exception.factory.SemanticException;
import node.RootNode;
import node.scope.ParameterNode;

public class DuplicateParameterException extends Exception implements SemanticException {

    public DuplicateParameterException(ParameterNode node){
        super(node.getLine()+"DuplicateParameterException- identifier \""
                +node.toString()+"\" already declared. ");
    }

    public DuplicateParameterException() {
    }

    public DuplicateParameterException(String message) {
        super(message);
    }

    public DuplicateParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateParameterException(Throwable cause) {
        super(cause);
    }

    public DuplicateParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String setErrorMessage(RootNode source, RootNode target) {
        return null;
    }
}
