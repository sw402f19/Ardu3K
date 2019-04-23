package node.primary;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ListElement extends AbstractPrimaryNode {

    @Override
    public String toString() { return "List element"; }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitListElement(this);
        else return visitor.visitChildren(this);
    }
}
