package visitor.semantic.typecast;

import exception.factory.SemanticException;
import exception.factory.ExceptionFactory;
import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.primary.AbstractPrimaryNode;
import visitor.semantic.PrimaryVisitor;

@SuppressWarnings("Duplicates")
public class ExpressionCastVisitor extends PrimaryVisitor {

    private RootNode expectedType;

    public RootNode initVisit(RootNode expectedType, RootNode node) throws SemanticException {
        this.expectedType = expectedType;
        visit(node);
        return node;
    }
    // todo fix exception handling to print the proper cause
    public RootNode visit(AbstractInfixExpressionNode node) throws SemanticException {
        RootNode[] infixTypes = new RootNode[2];
        try {
            infixTypes[0] = visit(node.getLeft());
            infixTypes[1] = visit(node.getRight());
        } catch (SemanticException e) {
            throw ExceptionFactory.produce("incompatibletype", node.getLeft(), node.getRight());
        }

        if (infixTypes[0] != null && !infixTypes[0].getClass().isInstance(expectedType)) {
            try {
                infixTypes[0] = TypeCaster.cast(infixTypes[0], expectedType);
            } catch (SemanticException e) {
                throw ExceptionFactory.produce("incompatibletypes", expectedType, infixTypes[0]);
            }
        }
        if (infixTypes[1] != null && !infixTypes[1].getClass().isInstance(expectedType)) {
            try {
                infixTypes[1] = TypeCaster.cast(infixTypes[1], expectedType);
            } catch (SemanticException e) {
                throw ExceptionFactory.produce("incompatibletypes", expectedType, infixTypes[1]);
            }
        }
        return expectedType;
    }

    public RootNode visit(AbstractPrimaryNode node) throws SemanticException {
        if(expectedType.getClass().isInstance(node))
            return super.visit(node);
        else
            node = (AbstractPrimaryNode) TypeCaster.cast(node, expectedType);
        return node;
    }
}
