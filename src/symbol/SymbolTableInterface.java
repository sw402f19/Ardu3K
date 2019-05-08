package symbol;

import exception.factory.SemanticException;
import node.RootNode;
import node.expression.DeclarationNode;

// This is an interface used for mock of working with a symbol table
// Based upon functions from Fishser page 292
public interface SymbolTableInterface {
    void openScope();
    void closeScope();
    Symbol retrieveSymbol(RootNode n);
    void enterSymbol(DeclarationNode n) throws SemanticException;
    boolean declaredLocally(RootNode node);
}
