package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class BreakNode extends RootNode {

    @Override
    public String toString() { return "BREAK"; }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitBreakNode(this);
        else return visitor.visitChildren(this);
    }
}
