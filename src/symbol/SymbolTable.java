package symbol;

import exception.factory.SemanticException;
import node.RootNode;
import node.expression.DeclarationNode;
import node.primary.AbstractPrimaryNode;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.scope.DefineNode;
import node.scope.FunctionNode;
import visitor.semantic.ExpressionTypeVisitor;

import java.util.HashMap;

public class SymbolTable implements SymbolTableInterface{

    private static SymbolTable thisInstance = new SymbolTable();
    private static HashMap<RootNode, Symbol> symTable = new HashMap<>();
    private static int depth;

    public void openScope() {
        depth++;
    }
    public void closeScope() {
        depth--;
        symTable.values().removeIf(e -> e.getDepth() > depth);
    }

    public void enterSymbol(DeclarationNode node) {
        symTable.put(node.getLeft(), new Symbol(node.getLeft(), node.type, depth));
    }

    public void enterSymbol(FunctionNode node){
        symTable.put(node.getId(), new FunctionSymbol(node.getId(), node, depth));
    }
    public void enterSymbol(IdentifierNode node, AbstractPrimaryNode type) {
        symTable.put(node, new Symbol(node, type, depth));
    }
    public void enterSymbol(IdentifierNode node){
        symTable.put(node, new Symbol(node, new UndefinedNode(node), depth));
    }
    public void enterSymbol(DefineNode node) {
        symTable.put(node.getId(), new Symbol(node.getId(), node.getValue(), depth));
    }
    public Symbol retrieveSymbol(RootNode name) {
        return symTable.get(name);
    }
    public boolean declaredLocally(RootNode node) {
        return symTable.get(node).getDepth() == depth;
    }
    public boolean isPresent(RootNode n) {
        return symTable.containsKey(n);
    }
    private SymbolTable() {
    }
    public static SymbolTable getInstance() {
        return thisInstance;
    }
}
