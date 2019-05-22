package exception.pins;

import exception.factory.SemanticException;
import node.statement.pins.PinWriteNode;

public class IllegalPinWriteValueException extends SemanticException {

    public IllegalPinWriteValueException() {
    }

    public IllegalPinWriteValueException(String message) {
        super(message);
    }

    public IllegalPinWriteValueException(Throwable cause) {
        super(cause);
    }

    public IllegalPinWriteValueException(PinWriteNode node) {
        super("IllegalPinWriteValueException: Value is not a valid write value");
    }
}