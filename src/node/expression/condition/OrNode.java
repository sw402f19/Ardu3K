package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class OrNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return "OR";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitOrNode(this);
        else return visitor.visitChildren(this);
    }
}
