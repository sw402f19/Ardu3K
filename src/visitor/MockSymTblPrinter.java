package visitor;

import node.RootNode;

// This is a class used for mock of working with a symbol table
// Based upon functions from Fishser page 292
// It will print every action to the console
public class MockSymTblPrinter implements MockSymTbl {
    @Override
    public void openScope() { System.out.print("I opened a scope"); }

    @Override
    public void closeScope() { System.out.print("I closed a scope"); }

    @Override
    public RootNode retriveSymbol(RootNode n) {
        System.out.print("I got a symbol");
        return null;
    }

    @Override
    public void enterSymbol(String name, char type) { System.out.print("I entered a symbol"); }

    @Override
    public Boolean declaredLocally(String name) {
        System.out.print("I opened a scope");
        return true;
    }
}
