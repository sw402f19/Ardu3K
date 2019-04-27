package visitor.semantic.typecast;

import exception.IllegalTypeException;
import exception.IncompatibleTypeExpection;
import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.primary.AbstractPrimaryNode;
import symbol.SymbolTable;
import visitor.semantic.PrimaryVisitor;

public class ExpressionCastVisitor extends PrimaryVisitor {

    private RootNode expectedType;

    public RootNode initVisit(RootNode expectedType, RootNode node) {
        this.expectedType = expectedType;
        return visit(node);
    }

    public RootNode visit(AbstractInfixExpressionNode node) throws IncompatibleTypeExpection {
        RootNode[] infixTypes = new RootNode[2];
        infixTypes[0] = visit(node.getLeft());
        infixTypes[1] = visit(node.getRight());

        try {
            if (!infixTypes[0].getClass().isInstance(expectedType)) {
                try {
                    infixTypes[0] = TypeCaster.cast(infixTypes[0], expectedType);
                } catch (IllegalTypeException e) {
                    throw new IncompatibleTypeExpection(infixTypes[0].line +
                            "Incompatible types: got " + infixTypes[0].toString() +
                            ", expected " + expectedType.toString());
                }
            }
            if (!infixTypes[1].getClass().isInstance(expectedType)) {
                try {
                    infixTypes[1] = TypeCaster.cast(infixTypes[1], expectedType);
                } catch (IllegalTypeException e) {
                    throw new IncompatibleTypeExpection(infixTypes[1].line +
                            "Incompatible types: got " + infixTypes[1].toString() +
                            ", expected " + expectedType.toString());
                }
            }
        } catch (Exception e) {

        }
        return expectedType;
    }
    public RootNode visit(AbstractPrimaryNode node) throws IllegalTypeException {
        if(expectedType.getClass().isInstance(node))
            return super.visit(node);
        else
            node = (AbstractPrimaryNode) TypeCaster.cast(node, expectedType);
        return node;
    }
}
