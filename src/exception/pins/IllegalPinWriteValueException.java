package exception.pins;

import exception.factory.SemanticException;
import node.statement.pins.PinIndexNode;
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
        super("IllegalPinWriteValueException: Value " + node.getWriteValue() + " is not valid to write (0 to 255)");
    }
}