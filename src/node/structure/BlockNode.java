package node.structure;

import node.RootNode;

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
