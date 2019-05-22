package node.statement.pins;

import gen.Ardu3kParser;
import node.RootNode;

public class PinWriteNode extends AbstractPinStmtNode {
    public PinWriteNode(Ardu3kParser.PinWriteBoolContext ctx) {
        super(ctx);
    }
    public PinWriteNode(Ardu3kParser.PinWriteIntContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "Write"; }

    public void setWriteValue(RootNode node) {
        if (children.size() > 1){
            children.set(1, node);
        } else children.add(node);
    }
    public RootNode getWriteValue() { return children.get(1); }
}
