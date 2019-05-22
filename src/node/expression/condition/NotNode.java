package node.expression.condition;

import gen.Ardu3kParser;

public class NotNode extends AbstractInfixConditionalNode {

    public NotNode(Ardu3kParser.InfixEqualExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "!=";
    }

}
