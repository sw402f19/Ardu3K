package node.primary;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class StringValNode extends AbstractPrimaryNode{
    public String value;

    public StringValNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitStringValNode(this);
        else return visitor.visitChildren(this);
    }
}
