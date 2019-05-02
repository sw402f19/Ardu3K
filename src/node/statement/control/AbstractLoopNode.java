package node.statement.control;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractLoopNode extends AbstractControlNode {
    public AbstractLoopNode(RootNode parent) {
        super(parent);
    }

    public AbstractLoopNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractLoopNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractLoopNode() {
    }
}
