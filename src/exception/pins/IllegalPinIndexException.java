package exception.pins;

import exception.factory.SemanticException;
import node.RootNode;
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
        super("IllegalPinIndexException: Index " + node.getIndex() + " in pin out of bound for boards up to Arduino Mega (0 to 14)");
    }
}
