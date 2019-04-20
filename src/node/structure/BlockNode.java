package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class BlockNode extends RootNode {

    public BlockNode(RootNode parent) {
        super(parent);
    }

    public BlockNode() {
    }

    @Override
    public String toString() {
        return "block";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitBlockNode(this);
        else return visitor.visitChildren(this);
    }
}
