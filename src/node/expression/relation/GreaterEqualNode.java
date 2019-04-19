package node.expression.relation;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class GreaterEqualNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return ">=";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitGreaterEqualNode(this);
        else return visitor.visitChildren(this);
    }
}
