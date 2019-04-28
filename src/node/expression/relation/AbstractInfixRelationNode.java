package node.expression.relation;

import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AbstractInfixNumeralNode;
import node.expression.type.BooleanType;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixRelationNode extends AbstractInfixNumeralNode {

    public AbstractInfixRelationNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixRelationNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixRelationNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixRelationNode(){

    }
}
