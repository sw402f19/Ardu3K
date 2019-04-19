package node.expression.relation;


import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class LesserEqualNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return  " <= "  ;
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitLesserEqualNode(this);
        else return visitor.visitChildren(this);
    }
}
