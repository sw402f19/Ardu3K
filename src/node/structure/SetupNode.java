package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class SetupNode extends RootNode {

    @Override
    public String toString() { return "Setup"; }

    public RootNode getBlock(){ return children.get(0); }

    public void setBlock(RootNode n){
        if (children.size() != 0) {
            children.set(0, n);
        } else {
            children.add(n);
        }
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitSetupNode(this);
        else return visitor.visitChildren(this);
    }
}
