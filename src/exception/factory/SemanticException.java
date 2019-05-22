package exception.factory;

public abstract class SemanticException extends RuntimeException {

    public SemanticException() {
    }

    public SemanticException(String message) {
        super(message);
    }

    public SemanticException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
