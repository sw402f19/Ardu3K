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

    private HashMap<RootNode, Symbol> symTable = new HashMap<>();
    private int depth;

    public SymbolTable() {
    }

    public void openScope() {
        depth++;
    }
    public void closeScope() {
        depth--;
        symTable.values().removeIf(e -> e.getDepth() > depth);
    }

    public void enterSymbol(DeclarationNode node) {
        symTable.put(node.getLeft(), new Symbol(node.type, depth));
    }
    public void enterSymbol(FunctionNode node) {
        symTable.put(node.getId(), new FunctionSymbol(node, null, depth));
    }

    public void enterSymbol(FunctionNode node, SymbolTable symbolTable){
        symTable.put(node.getId(), new FunctionSymbol(node, symbolTable, depth));
    }
    public void enterSymbol(IdentifierNode node, RootNode type) {
        symTable.put(node, new Symbol(type, depth));
    }
    public void enterSymbol(DefineNode node) {
        symTable.put(node.getId(), new Symbol(node.getValue(), depth));
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
    public HashMap<RootNode, Symbol> getTable(){
        return this.symTable;
    }
}
