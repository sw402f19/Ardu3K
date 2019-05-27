package exception.factory;

import java.util.ArrayList;

public class ExceptionCollector {
    private static ExceptionCollector collector = new ExceptionCollector();
    private static ArrayList<SemanticException> throwlist = new ArrayList<>();

    private ExceptionCollector() { }

    public static ExceptionCollector getInstance() {
        return collector;
    }

    public boolean shouldThrow() {
        return throwlist.size() > 5;
    }
    public int throwSize() {
        return throwlist.size();
    }
    public void throwList() throws FullCollectorException {
        throw new FullCollectorException();
    }
    public void appendException(SemanticException excep) {
        throwlist.add(excep);
    }
    public void handleException(SemanticException exception) throws FullCollectorException {
        if(collector.shouldThrow())
            throw new FullCollectorException();
        else
            collector.appendException(exception);
    }
    public ArrayList<SemanticException> getThrowlist() {
        return throwlist;
    }
}
