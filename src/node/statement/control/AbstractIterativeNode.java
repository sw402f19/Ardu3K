package node.statement.control;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractIterativeNode extends AbstractControlNode {
    public AbstractIterativeNode(RootNode parent) {
        super(parent);
    }

    public AbstractIterativeNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractIterativeNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractIterativeNode() {
    }
}
