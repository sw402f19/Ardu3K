package node.primary;

import node.RootNode;
import node.expression.type.BooleanType;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class BoolNode extends AbstractPrimaryNode implements BooleanType {
    public boolean value;

    public BoolNode(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitBoolNode(this);
        else return visitor.visitChildren(this);
    }
}
