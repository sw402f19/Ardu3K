package node.statement.pins;

import gen.Ardu3kParser;
import node.RootNode;

public class PinIndexNode extends RootNode {
    private boolean bAnalog = false;

    public PinIndexNode(Ardu3kParser.Pin_indexContext ctx) {
        super(ctx);
    }

    public void setIndex(RootNode index) {
        if(children.size() > 0)
            children.set(0, index);
        else
            children.add(index);
    }
    public RootNode getIndex() {
        if(children.get(0) != null)
            return children.get(0);
        else return null;
    }

    public void setbAnalog(boolean bAnalog) { this.bAnalog = bAnalog; }
    public boolean getbAnalog() { return bAnalog; }

    @Override
    public String toString() {
        return (bAnalog ? "A" : "") + children.get(0).toString();
    }
}
