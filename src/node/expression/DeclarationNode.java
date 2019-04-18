package node.expression;

public class DeclarationNode extends AbstractInfixExpressionNode {

    public String getName() {
        return getLeft().toString();
    }
}
