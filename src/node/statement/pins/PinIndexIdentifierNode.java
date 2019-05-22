package node.statement.pins;

import gen.Ardu3kParser;
import node.RootNode;

public class PinIndexIdentifierNode extends PinIndexNode {

    public PinIndexIdentifierNode(Ardu3kParser.PinIndexIntContext ctx) {
        super(ctx);
    }
    public PinIndexIdentifierNode(Ardu3kParser.PinIndexIdContext ctx) {
        super(ctx);
    }

    public void setID(RootNode node){
        if (children.size() > 0){
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getID() { return children.get(0); }
}
