package visitor.semantic;

import node.RootNode;
import node.expression.AssignmentNode;
import exception.IllegalTypeException;
import node.primary.AbstractPrimaryNode;
import node.primary.IdentifierNode;
import symbol.SymbolTable;
import visitor.BaseASTVisitor;

public class AssignmentVisitor extends BaseASTVisitor<RootNode> {

    private RootNode expectedType;
    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AssignmentNode node) throws IllegalTypeException {
        expectedType = symbolTable.retrieveSymbol(node.getLeft()).getType();
        RootNode expressionType = new ExpressionTypeVisitor().visit(node.getRight());

        if(expressionType == null)
            throw new IllegalTypeException("Expression returned null, incomplete visit methods");
        if(isInstanceOf(expressionType.getClass(), expectedType))
            return node;
        else
            throw new IllegalTypeException(node.getLine()+" Illegal type: "
                    +expressionType.toString()+" for identifier "+node.getLeft()
                    +", expected " +expectedType.toString());
    }
    public RootNode visit(AbstractPrimaryNode node) throws IllegalTypeException {
        if(!isInstanceOf(expectedType.getClass(), node))
            throw new IllegalTypeException(
                    node.getLine()+" Incompatible types "+node.toString()+", expected "+expectedType.toString());
        return node;
    }
    public RootNode visit(IdentifierNode node) {
        return symbolTable.retrieveSymbol(node).getType();
    }

    public boolean isInstanceOf(Class clazz, Object obj) {
        return clazz.isInstance(obj);
    }
}
