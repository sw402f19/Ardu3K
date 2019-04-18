package node.expression;

public class DeclarationNode extends AbstractInfixExpressionNode {

    public DeclarationNode(AbstractInfixExpressionNode node) {
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
    }

    public String getName() {
        return getLeft().toString();
    }
}
