package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class LoopNode extends RootNode {

    @Override
    public String toString() {
        return "Loop";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitLoopNode(this);
        else return visitor.visitChildren(this);
    }
}
