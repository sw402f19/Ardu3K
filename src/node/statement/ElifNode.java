package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ElifNode extends IfNode {

    @Override
    public String toString() {
        return "if do else ";
    }

    public RootNode getLowerbody() {
        return children.get(0);
    }
    public void setLowerbody(RootNode lowerbody) {
        children.set(0, lowerbody);
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitElifNode(this);
        else return visitor.visitChildren(this);
    }

}
