package node.statement.pins;

import node.RootNode;

public class AbstractPinStmtNode extends RootNode {
    public void setPinIndexNode(PinIndexNode node) {
        if (children.size() > 0) {
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getPinIndexNode() { return children.get(0); }
}
