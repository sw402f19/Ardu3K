package visitor.semantic;

import node.RootNode;
import node.expression.AssignmentNode;
import exception.IllegalTypeException;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
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
            throw new IllegalTypeException("DEV ERROR: Expression returned null, incomplete visit methods in AssignmentVisitor");
        //if(isInstanceOf(expressionType.getClass(), expectedType))
        if(isInstanceOf(expectedType, expressionType))
            return node;
        else
            throw new IllegalTypeException(node.getLine()+" Illegal type: "
                    +expressionType.toString()+" for identifier "+node.getLeft()
                    +", expected " +expectedType.toString());
    }
    public RootNode visit(AbstractPrimaryNode node) throws IllegalTypeException {
        if(!isInstanceOf(expectedType, node))
            throw new IllegalTypeException(
                    node.getLine()+" Incompatible types "+node.toString()+", expected "+expectedType.toString());
        return node;
    }
    public RootNode visit(IdentifierNode node) {
        return symbolTable.retrieveSymbol(node).getType();
    }

    private boolean isInstanceOf(RootNode expected, RootNode source) {
        if(source instanceof NumeralType)
            return expected instanceof NumeralType;
        return expected instanceof BooleanType;
    }
}
