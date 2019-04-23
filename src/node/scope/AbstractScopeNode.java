package node.scope;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractScopeNode extends RootNode {

    public AbstractScopeNode(RootNode parent) {
        super(parent);
    }

    public AbstractScopeNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractScopeNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractScopeNode() {
    }
}
