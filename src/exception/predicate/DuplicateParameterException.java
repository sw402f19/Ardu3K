package exception.predicate;

import exception.factory.SemanticException;
import node.RootNode;
import node.primary.IdentifierNode;

public class DuplicateParameterException extends SemanticException {

    public DuplicateParameterException(IdentifierNode node, RootNode prev){
        super(node.getLine()+" DuplicateParameterException: identifier \""
                +node.toString()+"\" already declared at "+ prev.line);
    }

    public DuplicateParameterException(Throwable cause) {
        super(cause);
    }
}
