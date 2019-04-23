package node.scope;

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

}
