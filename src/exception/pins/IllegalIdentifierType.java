package exception.pins;

import exception.factory.SemanticException;
import node.statement.pins.PinIndexNode;

public class IllegalIdentifierType  extends SemanticException {

    public IllegalIdentifierType() {
    }

    public IllegalIdentifierType(String message) {
        super(message);
    }

    public IllegalIdentifierType(Throwable cause) {
        super(cause);
    }

    public IllegalIdentifierType(PinIndexNode node) {
        super(node.line + " IllegalIdentifierType: Invalid type of identifier being used as index! Only int allowed");
    }
}
