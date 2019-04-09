package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {
    public RootNode left;
    public RootNode right;

    public AbstractInfixExpressionNode(Ardu3kParser.ExpressionContext ctx){
        super(ctx);
    }

    public AbstractInfixExpressionNode(){

    }

    public AbstractInfixExpressionNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    public AbstractInfixExpressionNode(Ardu3kParser.ConditionalEqualExprContext ctx) {
        super(ctx);
    }
}
