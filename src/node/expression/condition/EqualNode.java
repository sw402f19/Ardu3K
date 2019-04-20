package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class EqualNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return " == ";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitEqualNode(this);
        else return visitor.visitChildren(this);
    }
}
