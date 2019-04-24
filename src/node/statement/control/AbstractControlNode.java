package node.statement.control;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractControlNode extends RootNode {

    public AbstractControlNode(RootNode parent) {
        super(parent);
    }

    public AbstractControlNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractControlNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractControlNode() {
    }

    public RootNode getExpression() {
        return children.get(0);
    }
    public void setExpression(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

}
