package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ContinueNode extends RootNode {
    @Override
    public String toString() { return "CONTINUE"; }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitContinueNode(this);
        else return visitor.visitChildren(this);
    }
}
