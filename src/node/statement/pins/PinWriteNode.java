package node.statement.pins;

import gen.Ardu3kParser;
import node.RootNode;
import node.primary.BoolNode;

public class PinWriteNode extends AbstractPinStmtNode {
    public PinWriteNode(Ardu3kParser.PinWriteContext ctx) {
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
