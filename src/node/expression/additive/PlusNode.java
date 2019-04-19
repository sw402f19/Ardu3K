package node.expression.additive;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class PlusNode extends AbstractInfixAdditiveNode{

    @Override
    public String toString() {
        return  " + " ;
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitPlusNode(this);
        else return visitor.visitChildren(this);
    }
}
