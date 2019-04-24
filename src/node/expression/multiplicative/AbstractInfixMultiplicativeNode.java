package node.expression.multiplicative;

import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.type.NumeralType;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractInfixMultiplicativeNode extends AbstractInfixExpressionNode
        implements NumeralType {

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
