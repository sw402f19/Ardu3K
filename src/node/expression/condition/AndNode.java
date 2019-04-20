package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class AndNode extends AbstractInfixConditionalNode{

    @Override
    public String toString() {
        return  "AND";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitAndNode(this);
        else return visitor.visitChildren(this);
    }
}
