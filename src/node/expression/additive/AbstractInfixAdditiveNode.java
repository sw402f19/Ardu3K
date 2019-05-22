package node.expression.additive;

import node.RootNode;
import node.expression.AbstractInfixNumeralNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixAdditiveNode extends AbstractInfixNumeralNode {
    public AbstractInfixAdditiveNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixAdditiveNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixAdditiveNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixAdditiveNode() {
    }
}
