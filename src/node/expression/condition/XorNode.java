package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class XorNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return "XOR";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitXorNode(this);
        else return visitor.visitChildren(this);
    }
}
