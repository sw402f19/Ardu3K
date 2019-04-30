package node.expression;

import node.RootNode;
import node.expression.type.NumeralType;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractInfixNumeralNode extends AbstractInfixExpressionNode implements NumeralType{

    public AbstractInfixNumeralNode(RootNode parent) {
        super(parent);
    }

    public AbstractInfixNumeralNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractInfixNumeralNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractInfixNumeralNode() {
    }
}
