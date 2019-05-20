package node.statement.pins;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractPinStmtNode extends RootNode {
    public AbstractPinStmtNode(RootNode parent) {
        super(parent);
    }

    public AbstractPinStmtNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractPinStmtNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractPinStmtNode() {
    }

    public void setPinIndexNode(RootNode node) {
        if (children.size() > 0) {
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getPinIndexNode() { return children.get(0); }
}
