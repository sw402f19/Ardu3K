package node.primary;

import node.expression.type.NumeralType;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AbstractNumberNode extends AbstractPrimaryNode
        implements NumeralType {

    public AbstractNumberNode(ParserRuleContext ctx) {
        super(ctx);
    }

    protected AbstractNumberNode() {
    }
}
