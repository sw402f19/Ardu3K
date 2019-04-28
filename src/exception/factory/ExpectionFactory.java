package exception.factory;

import exception.IllegalTypeException;

public class ExpectionFactory {

    public static SemanticException produce(String excp) throws NoProductException {
        switch (excp.toUpperCase()) {
            case "IllegalType":
                return new IllegalTypeException();

                default:
                    throw new NoProductException("");
        }
    }
}
