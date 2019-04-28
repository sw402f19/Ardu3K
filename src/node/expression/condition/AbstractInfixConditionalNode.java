package node.expression.condition;

import node.RootNode;
import node.expression.AbstractInfixBooleanNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.type.BooleanType;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixConditionalNode extends AbstractInfixBooleanNode {

    public AbstractInfixConditionalNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixConditionalNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixConditionalNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixConditionalNode() {
    }
}
