package visitor.semantic.typecast;

import exception.IllegalTypeException;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.primary.AbstractPrimaryNode;
import visitor.semantic.PrimaryVisitor;

@SuppressWarnings("Duplicates")
public class ExpressionCastVisitor extends PrimaryVisitor {

    private RootNode expectedType;

    public RootNode initVisit(RootNode expectedType, RootNode node) {
        this.expectedType = expectedType;
        return visit(node);
    }
    public RootNode visit(AbstractInfixExpressionNode node) throws SemanticException {
        RootNode[] infixTypes = new RootNode[2];
        infixTypes[0] = visit(node.getLeft());
        infixTypes[1] = visit(node.getRight());

        if (infixTypes[0] != null && !infixTypes[0].getClass().isInstance(expectedType)) {
            try {
                infixTypes[0] = TypeCaster.cast(infixTypes[0], expectedType);
            } catch (IllegalTypeException e) {
                throw ExceptionFactory.produce("incompatibletypes", infixTypes[0], expectedType);
            }
        }
        if (infixTypes[1] != null && !infixTypes[1].getClass().isInstance(expectedType)) {
            try {
                infixTypes[1] = TypeCaster.cast(infixTypes[1], expectedType);
            } catch (IllegalTypeException e) {
                throw ExceptionFactory.produce("incompatibletypes", infixTypes[1], expectedType);
            }
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
