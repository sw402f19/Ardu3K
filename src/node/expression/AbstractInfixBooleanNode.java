package node.expression;

import node.RootNode;
import node.expression.type.BooleanType;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixBooleanNode extends AbstractInfixExpressionNode implements BooleanType {
    public AbstractInfixBooleanNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixBooleanNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixBooleanNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixBooleanNode() {
    }
}
