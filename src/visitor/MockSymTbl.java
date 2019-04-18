package visitor;

import node.RootNode;

// This is an interface used for mock of working with a symbol table
// Based upon functions from Fishser page 292
public interface MockSymTbl {
    void openScope();
    void closeScope();
    RootNode retriveSymbol(RootNode n);
    void enterSymbol(String name, char type);
    Boolean declaredLocally(String name);
}
