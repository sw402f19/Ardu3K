package node.structure;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ParameterNode extends RootNode {

    public ParameterNode() {
    }

    @Override
    public String toString() {
        return "Parameter";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitParameterNode(this);
        else return visitor.visitChildren(this);
    }
}
