package node.structure;

import node.RootNode;

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
}
