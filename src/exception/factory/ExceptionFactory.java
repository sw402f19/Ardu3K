package exception.factory;

import exception.ArgumentException;
import exception.CodeGenTypeException;
import exception.TimedTimeException;
import exception.pins.IllegalIdentifierType;
import exception.pins.IllegalPinIndexException;
import exception.pins.IllegalPinWriteValueException;
import exception.predicate.*;
import exception.reachability.NoReturnException;
import exception.reachability.NotReachableException;
import exception.reachability.RecursionException;
import exception.time.NoTimeTypeException;
import exception.time.NoTimerException;
import exception.type.IllegalOperandException;
import exception.type.IllegalTypeException;
import exception.type.IncompatibleTypeExpection;
import exception.type.NotCastableException;
import node.RootNode;
import node.primary.AbstractPrimaryNode;
import node.primary.IdentifierNode;
import node.scope.FunctionNode;
import node.statement.control.AbstractControlNode;
import node.statement.pins.PinIndexIdentifierNode;
import node.statement.pins.PinIndexNode;
import node.statement.pins.PinWriteNode;
import node.statement.termination.AbstractTerminalNode;
import node.statement.termination.ReturnNode;
import node.statement.time.AbstractTimeStmtNode;
import node.statement.time.ResetNode;
import node.statement.time.ResetSpecificNode;

public class ExceptionFactory {

    public static SemanticException produce(String exceptionClassName, RootNode node) throws NoProductException {
        switch (exceptionClassName
                .toUpperCase()
                .replaceAll(" ", "")
                .replaceAll("EXCEPTION", "")) {
            case "UNDECLAREDIDENTIFIER":
                return new UndeclaredIdentifierException((IdentifierNode) node);

            case "NEEDSBOOLEANPREDICATE":
                return new NeedsBooleanPredicateException((AbstractControlNode) node);

            case "ILLEGALPININDEX":
                return new IllegalPinIndexException((PinIndexNode) node);

            case "ILLEGALPINWRITE":
                return new IllegalPinWriteValueException((PinWriteNode) node);

            case "TIMEDTIME":
                return new TimedTimeException(node);

            case "NEEDSTIMEPREDICATE":
                return new NeedsTimePredicateException((AbstractPrimaryNode) node);

            case "NOTIMER":
                return new NoTimerException((ResetNode) node);

            case "NOTIMERSPECIFIC":
                return new NoTimerException((ResetSpecificNode) node);

            case "INVALIDTIMETYPE":
                return new NoTimeTypeException((AbstractTimeStmtNode) node);

            case "CODEGENTYPE":
                return new CodeGenTypeException(node);

            case "PININDEXID":
                return new IllegalIdentifierType((PinIndexIdentifierNode) node);

            case "NORETURN":
                return new NoReturnException((FunctionNode)node);

            case "NOTREACHABLE":
                if(node instanceof ReturnNode)
                    return new NotReachableException((ReturnNode)node);
                else
                    return new NotReachableException((AbstractTerminalNode) node);

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

            case "INCOMPATIBLETYPE":
                return new IncompatibleTypeExpection(src, target);

            case "NOTCASTABLE":
                return new NotCastableException(src, target);

            case "NEEDSNUMERALPREDICATE":
                return new NeedsNumeralPredicateException((AbstractControlNode) src, target);

            case "DUPLICATEPARAMETER":
                return new DuplicateParameterException((IdentifierNode) src, target);

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
        else if (throwable instanceof NeedsNumeralPredicateException)
            return new NeedsNumeralPredicateException(throwable);
        else if (throwable instanceof NotCastableException)
            return new NotCastableException(throwable);
        else if (throwable instanceof UndeclaredIdentifierException)
            return new UndeclaredIdentifierException(throwable);
        else if (throwable instanceof RecursionException)
            return new RecursionException(throwable);
        else if(throwable instanceof NotReachableException)
            return new NotReachableException(throwable);
        else if(throwable instanceof ArgumentException)
            return new NotReachableException(throwable);
        else if (throwable instanceof IllegalPinIndexException)
            return new IllegalPinIndexException(throwable);
        else if (throwable instanceof  IllegalPinWriteValueException)
            return new IllegalPinWriteValueException(throwable);
        else if (throwable instanceof TimedTimeException)
            return new TimedTimeException(throwable);
        else if (throwable instanceof NoTimerException)
            return new NoTimerException(throwable);
        else if(throwable instanceof NeedsTimePredicateException)
            return new NeedsTimePredicateException(throwable);
        else if (throwable instanceof NoTimeTypeException)
            return new NoTimeTypeException(throwable);
        else if (throwable instanceof CodeGenTypeException)
            return new CodeGenTypeException(throwable);
        else if (throwable instanceof  IllegalIdentifierType)
            return new IllegalIdentifierType(throwable);

        else throw new NoProductException(throwable.getClass().getSimpleName());
    }
}
