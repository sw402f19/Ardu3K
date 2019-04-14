package node.primary;

import node.RootNode;

public class BoolNode extends AbstractPrimaryNode {
    public boolean value;

    public BoolNode(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
