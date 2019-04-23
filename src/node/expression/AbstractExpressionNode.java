package node.expression;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractExpressionNode extends RootNode {

    public AbstractExpressionNode(RootNode parent) {
        super(parent);
    }

    public AbstractExpressionNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractExpressionNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractExpressionNode() {
    }
}
