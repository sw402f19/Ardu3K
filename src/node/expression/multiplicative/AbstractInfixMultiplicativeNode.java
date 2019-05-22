package node.expression.multiplicative;

import node.RootNode;
import node.expression.AbstractInfixNumeralNode;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixMultiplicativeNode extends AbstractInfixNumeralNode {

    public AbstractInfixMultiplicativeNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixMultiplicativeNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixMultiplicativeNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixMultiplicativeNode() {
    }
}
