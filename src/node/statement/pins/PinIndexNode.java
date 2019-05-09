package node.statement.pins;

import node.RootNode;

public class PinIndexNode extends RootNode {
    private int index;
    private boolean bAnalog = false;

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    public void setbAnalog(boolean bAnalog) { this.bAnalog = bAnalog; }
    public boolean getbAnalog() { return bAnalog; }

    @Override
    public String toString() {
        return (bAnalog ? "A" : "") + index;
    }
}