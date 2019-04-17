package node.statement;

import node.RootNode;

public class WhileNode extends AbstractStatementNode {
    @Override
    public String toString() { return "While do"; }

    public RootNode getExpressionNode() { return children.get(0); }
    public void setExpressionNode(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getStmt() { return children.get(1); }
    public void setStmt(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
