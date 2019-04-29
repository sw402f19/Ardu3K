package exception.factory;

import exception.*;
import node.RootNode;
import node.primary.IdentifierNode;
import node.statement.control.WhileNode;

public class ExceptionFactory {

    public static SemanticException produce(String exceptionClassName, RootNode node) throws NoProductException {
        switch (exceptionClassName
                .toUpperCase()
                .replaceAll(" ", "")
                .replaceAll("EXCEPTION", "")) {
            case "UNDECLAREDIDENTIFIER":
                return new UndeclaredIdentifierException((IdentifierNode) node);

            case "DUPLICATEPARAMETER":
                return new DuplicateParameterException((IdentifierNode) node);

            case "NEEDSBOOLEANPREDICATE":
                return new NeedsBooleanPredicateException((WhileNode) node);

            default:
                throw new NoProductException(exceptionClassName);
        }
    }

    public static SemanticException produce(String exceptionClassName, RootNode src, RootNode target) {
        switch (exceptionClassName
                .toUpperCase()
                .replaceAll(" ", "")
                .replaceAll("EXCEPTION", "")) {
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

    public static SemanticException produce(Throwable throwable) {
        if (throwable instanceof DuplicateParameterException)
            return new DuplicateParameterException(throwable);
        else if (throwable instanceof IllegalOperandException)
            return new IllegalOperandException(throwable);
        else if (throwable instanceof IllegalTypeException)
            return new IllegalTypeException(throwable);
        else if (throwable instanceof IncompatibleTypeExpection)
            return new IncompatibleTypeExpection(throwable);
        else if (throwable instanceof NeedsBooleanPredicateException)
            return new NeedsBooleanPredicateException(throwable);
        else if (throwable instanceof NotCastableException)
            return new NotCastableException(throwable);
        else if (throwable instanceof UndeclaredIdentifierException)
            return new UndeclaredIdentifierException(throwable);
        else throw new NoProductException(throwable.getClass().getSimpleName());
    }
}
