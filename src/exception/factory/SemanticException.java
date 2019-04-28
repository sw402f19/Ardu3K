package exception.factory;

import node.RootNode;

public interface SemanticException {

    public String setErrorMessage(RootNode source, RootNode target);

    String errorMessage = "";
}
