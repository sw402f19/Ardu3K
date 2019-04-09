package ASTVisitor.expression.relation;

import ASTVisitor.expression.AbstractExpressionNode;
import ASTVisitor.expression.AbstractInfixExpressionNode;
import gen.Ardu3kParser;

public abstract class AbstractInfixRelationNode extends AbstractInfixExpressionNode {

    public AbstractInfixRelationNode(){

    }

    public AbstractInfixRelationNode(Ardu3kParser.InfixRelationalExprContext ctx){
        super(ctx);
    }

    public AbstractInfixRelationNode(Ardu3kParser.ConditionalEqualExprContext ctx) {
        super(ctx);
    }
}
