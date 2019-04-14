package node.statement;

import node.RootNode;
import node.structure.BlockNode;

public abstract class AbstractStatementNode extends BlockNode {
    public AbstractStatementNode() {
    }

    public AbstractStatementNode(RootNode parent) {
        super(parent);
    }

}
