package node.expression;

import node.RootNode;


public class DeclarationNode extends AbstractDeclAssignNode {

    public RootNode type;

    public DeclarationNode(AbstractInfixExpressionNode node) {
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
        this.parent = node.parent;
        this.line = node.line;
    }

    public String getName() {
        return getLeft().toString();
    }

}
