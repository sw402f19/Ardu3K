package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DefaultNode extends RootNode {

    @Override
    public String toString() {
        return "default ";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitDefaultNode(this);
        else return visitor.visitChildren(this);
    }
}
