package node.statement;

import node.RootNode;

public class CaseNode extends RootNode {
    public RootNode expression;

    @Override
    public String toString() {
        return "case ";
    }

    public RootNode getExpression() {
        return children.get(0);
    }
    public void setExpression(RootNode expression) {
        children.set(0, expression);
    }
}