package symbol;

import node.RootNode;
import node.expression.DeclarationNode;
import node.primary.IdentifierNode;

import java.util.HashMap;

public class SymbolTable {

    private static SymbolTable thisInstance;
    private static HashMap<? super RootNode, Symbol> symTable = new HashMap<>();
    private static int depth;

    public void openScope() {
        depth++;
    }
    public void closeScope() {
        depth--;
        symTable.values().removeIf(e -> e.getDepth() > depth);
    }

    public void enterSymbol(DeclarationNode node) {
        symTable.put(node.getLeft(), new Symbol(node.getLeft(), node.getRight(), depth));
    }
    public Symbol retrieveSymbol(IdentifierNode name) {
        return symTable.get(name);
    }
    public boolean declaredLocally(DeclarationNode node) {
        return symTable.get(node).getDepth() == depth;
    }


    private SymbolTable() {
    }
    public SymbolTable getInstance() {
        return thisInstance;
    }
    public SymbolTable getNewInstance() {
        return new SymbolTable();
    }
}
