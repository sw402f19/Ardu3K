package visitor;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.type.IllegalTypeException;
import node.primary.AbstractPrimaryNode;
import node.primary.IdentifierNode;
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
    public RootNode visit(AbstractPrimaryNode node) throws IllegalTypeException {
        if(!isInstanceOf(expectedType.getClass(), node))
            throw new IllegalTypeException(
                    node.getLine()+" Incompatible types "+node.getClass().getSimpleName()+", expected "+expectedType.getClass().getSimpleName());
        return node;
    }
    public RootNode visit(IdentifierNode node) {
        return symbolTable.retrieveSymbol(node).getType();
    }

    public boolean isInstanceOf(Class clazz, Object obj) {
        return clazz.isInstance(obj);
    }
}
