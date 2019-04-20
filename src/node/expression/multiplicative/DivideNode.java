package node.expression.multiplicative;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DivideNode extends AbstractInfixMultiplicativeNode {

    @Override
    public String toString() {
        return "/";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitDivideNode(this);
        else return visitor.visitChildren(this);
    }
}
