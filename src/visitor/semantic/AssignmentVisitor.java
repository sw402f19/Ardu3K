package visitor.semantic;

import exception.factory.SemanticException;
import node.RootNode;
import node.expression.AssignmentNode;
import exception.IllegalTypeException;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.primary.AbstractPrimaryNode;
import symbol.SymbolTable;
import visitor.semantic.typecast.ExpressionCastVisitor;

public class AssignmentVisitor extends PrimaryVisitor {

    private RootNode expectedType;
    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AssignmentNode node) throws SemanticException {
        expectedType = symbolTable.retrieveSymbol(node.getLeft()).getType();
        new ExpressionCastVisitor().initVisit(expectedType, node.getRight());
        return node;
    }
    public RootNode visit(AbstractPrimaryNode node) throws SemanticException {
        if(!isInstanceOf(expectedType, node))
            throw new IllegalTypeException(
                    node.getLine()+" Incompatible types "+node.toString()+", expected "+expectedType.toString());
        return node;
    }

    private boolean isInstanceOf(RootNode expected, RootNode source) {
        if(source instanceof NumeralType)
            return expected instanceof NumeralType;
        return expected instanceof BooleanType;
    }
}
