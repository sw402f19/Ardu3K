package exception;

import exception.factory.SemanticException;
import node.RootNode;
import node.primary.IdentifierNode;
import node.scope.ParameterNode;
import symbol.SymbolTable;

public class DuplicateParameterException extends SemanticException {

    public DuplicateParameterException(IdentifierNode node){
        super(node.getLine()+" DuplicateParameterException: identifier \""
                +node.toString()+"\" already declared at "+ SymbolTable.getInstance().retrieveSymbol(node).getName().line);
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
}
