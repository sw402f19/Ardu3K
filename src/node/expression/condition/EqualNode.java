package node.expression.condition;

import gen.Ardu3kParser;

public class EqualNode extends AbstractInfixConditionalNode {

    public EqualNode(Ardu3kParser.InfixEqualExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return " == ";
    }

}
