package node.expression.unary;

import node.expression.AbstractExpressionNode;
import visitor.ASTVisitor;

public class UnaryNode extends AbstractExpressionNode {
    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return null;
    }
}
