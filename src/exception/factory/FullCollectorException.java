package exception.factory;

import java.util.ArrayList;

public class FullCollectorException extends SemanticException{

    public FullCollectorException() {
    }

    public FullCollectorException(String message) {
        super(message);
    }

    public FullCollectorException(Throwable cause) {
        super(cause);
    }
}
