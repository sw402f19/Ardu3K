package exception.pins;

import exception.factory.SemanticException;
import node.statement.pins.PinIndexNode;

public class IllegalPinIndexException extends SemanticException {

    public IllegalPinIndexException() {
    }

    public IllegalPinIndexException(String message) {
        super(message);
    }

    public IllegalPinIndexException(Throwable cause) {
        super(cause);
    }

    public IllegalPinIndexException(PinIndexNode node) {
        super("IllegalPinIndexException: " + (node.getbAnalog() ? "Analog" : "Digital")  + " index " + node.getIndex() + " in pin operation out of bound for boards up to Arduino Uno");
    }
}
