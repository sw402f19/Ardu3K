package node.statement;

import node.structure.RootNode;

public class ForNode extends AbstractStatementNode {
    @Override
    public String toString() {
        return "for to do ";
    }

    public RootNode getExpressionNode() {
        return children.get(0);
    }
    public void setExpressionNode(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getValue() {
        return children.get(1);
    }
    public void setValue(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
    public RootNode getBlock() {
        return children.get(2);
    }
    public void setBlock(RootNode node) {
        if(children.size() > 2)
            children.set(2, node);
        else
            children.add(node);
    }
}
