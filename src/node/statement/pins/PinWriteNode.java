package node.statement.pins;

import node.RootNode;

public class PinWriteNode extends AbstractPinStmtNode {
    int writeValue;

    @Override
    public String toString() { return "Write"; }

    public void setWriteValue(int value) { writeValue = value; }
    public int getWriteValue() { return writeValue; }
}
