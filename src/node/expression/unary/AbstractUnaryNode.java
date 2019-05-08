package node.expression.unary;

import node.RootNode;
import node.expression.AbstractExpressionNode;

public abstract class AbstractUnaryNode extends AbstractExpressionNode {

    public AbstractUnaryNode() { super(); }

    public String getUnarySymbol() { return ""; }

    public void setExpr(RootNode node) {
        if (children.size() > 0) {
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getExpr() { return children.get(0); }
}
