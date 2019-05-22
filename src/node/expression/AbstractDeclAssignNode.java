package node.expression;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractDeclAssignNode extends AbstractInfixExpressionNode {
    public AbstractDeclAssignNode(RootNode parent) {
        super(parent);
    }

    public AbstractDeclAssignNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractDeclAssignNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractDeclAssignNode() {
    }
}
