package exception.factory;

import exception.*;
import node.RootNode;
import node.primary.IdentifierNode;
import node.scope.ParameterNode;
import node.statement.control.WhileNode;

public class ExceptionFactory {

    public static SemanticException produce(String exceptionClassName, RootNode node) throws NoProductException {
        switch (exceptionClassName
                .toUpperCase()
                .replaceAll(" ", "")
                .replaceAll("EXCEPTION","")) {
            case "UNDECLAREDIDENTIFIER":
                return new UndeclaredIdentifierException((IdentifierNode)node);

            case "DUPLICATEPARAMETER":
                return new DuplicateParameterException((IdentifierNode) node);

            case "NEEDSBOOLEANPREDICATE":
                return new NeedsBooleanPredicateException((WhileNode) node);

                default:
                    throw new NoProductException(exceptionClassName);
        }
    }
    public static SemanticException produce(String exceptionClassName, RootNode src, RootNode target) {
        switch(exceptionClassName
                .toUpperCase()
                .replaceAll(" ", "")
                .replaceAll("EXCEPTION","")) {
            case "ILLEGALOPERAND":
                return new IllegalOperandException(src, target);

            case "INCOMPATIBLETYPES":
                return new IncompatibleTypeExpection(src, target);

            case "NOTCASTABLE":
                return new NotCastableException(src, target);

                default:
                    throw new NoProductException(exceptionClassName);
        }
    }
}
