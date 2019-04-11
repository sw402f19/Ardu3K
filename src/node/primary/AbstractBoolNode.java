package node.primary;

import node.structure.RootNode;

public abstract class AbstractBoolNode extends RootNode {
    public boolean value;

    public AbstractBoolNode(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
