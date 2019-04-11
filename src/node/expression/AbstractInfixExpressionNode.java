package node.expression;

import node.structure.RootNode;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {

    public RootNode getLeft() {
        return children.get(0);
    }
    public RootNode getRight() {
        return children.get(1);
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
