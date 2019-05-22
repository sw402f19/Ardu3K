package node.expression;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {

    public AbstractInfixExpressionNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixExpressionNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixExpressionNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixExpressionNode() {
    }

    public RootNode getLeft() {
        if (children.size() > 0) {
            return children.get(0);
        } else return null;
    }
    public RootNode getRight() {
        if (children.size() > 1) {
            return children.get(1);
        } else return null;
    }
    public void setLeft(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }
    public void setRight(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
