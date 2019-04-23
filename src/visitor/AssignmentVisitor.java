package visitor;

import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.type.IllegalTypeException;
import symbol.SymbolTable;

public class AssignmentVisitor extends BaseASTVisitor<RootNode> {

    private RootNode expectedType;
    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AssignmentNode node) throws IllegalTypeException {
        expectedType = symbolTable.retrieveSymbol(node.getLeft()).getType();
        RootNode expressionTree = visit(node.getRight());

        if(expressionTree == null)
            throw new IllegalTypeException("Expression returned null, incomplete visit methods");
        if(isInstanceOf(expressionTree.getClass(), expectedType))
            return node;
        else
            throw new IllegalTypeException(node.getLine()+" Illegal type: "+expressionTree.toString()+" for "+node.toString());
    }


    public boolean isInstanceOf(Class clazz, Object obj) {
        return clazz.isInstance(obj);
    }

}
