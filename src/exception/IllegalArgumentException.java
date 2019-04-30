package exception;

import exception.factory.SemanticException;
import node.RootNode;
import node.primary.IdentifierNode;
import symbol.SymbolTable;

public class IllegalArgumentException extends SemanticException {
    public IllegalArgumentException(IdentifierNode node) {
        super(node.getLine()+" IllegalArgumentException: identifier \""+node.toString()+"\""+
                " not of type "+ SymbolTable.getInstance().retrieveSymbol(node).getType().toString());
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
    public IllegalArgumentException() {}
}
