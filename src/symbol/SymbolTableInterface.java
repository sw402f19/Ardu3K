package symbol;

import node.RootNode;
import node.expression.DeclarationNode;

// This is an interface used for mock of working with a symbol table
// Based upon functions from Fishser page 292
public interface SymbolTableInterface {
    void openScope();
    void closeScope();
    Symbol retrieveSymbol(RootNode n);
    void enterSymbol(DeclarationNode n);
    boolean declaredLocally(RootNode node);
}
