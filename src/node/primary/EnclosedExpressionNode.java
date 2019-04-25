package node.primary;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class EnclosedExpressionNode extends AbstractExpressionNode {
    public EnclosedExpressionNode(RootNode parent) {
        super(parent);
    }

    public EnclosedExpressionNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public EnclosedExpressionNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public EnclosedExpressionNode() {
    }

    public void setExpression(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }
    public RootNode getExpression(RootNode node) {
        if (children.size() > 0) {
            return children.get(0);
        } else return null;
    }
}
