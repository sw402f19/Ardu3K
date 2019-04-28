package exception.factory;

import exception.DuplicateParameterException;
import exception.UndeclaredIdentifierException;
import node.RootNode;
import node.primary.IdentifierNode;
import node.scope.ParameterNode;

public class ExpectionFactory {

    public static SemanticException produce(String exceptionClassName, RootNode node) throws NoProductException {
        switch (exceptionClassName.toUpperCase()) {
            case "ILLEGALTYPE":
                return new UndeclaredIdentifierException((IdentifierNode)node);

            case "DUPLICATEPARAMETER":
                return new DuplicateParameterException((ParameterNode) node);

                default:
                    throw new NoProductException(exceptionClassName);
        }
    }
}
