package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {

    public RootNode getLeft() {
        return children.get(0);
    }
    public RootNode getRight() {
        return children.get(1);
    }
    public void setLeft(RootNode left) {
        children.set(0, left);
    }
    public void setRight(RootNode right) {
        children.set(1, right);
    }
}
