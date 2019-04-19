package node.expression;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class AssignmentNode extends AbstractInfixExpressionNode {

    @Override
    public String toString() {
        return "=";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitAssignmentNode(this);
        else return visitor.visitChildren(this);
    }
}
