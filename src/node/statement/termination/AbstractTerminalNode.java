package node.statement.termination;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractTerminalNode extends RootNode {

    public AbstractTerminalNode(RootNode parent) {
        super(parent);
    }

    public AbstractTerminalNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractTerminalNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractTerminalNode() {
    }
}
