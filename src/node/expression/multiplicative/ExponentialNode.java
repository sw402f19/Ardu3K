package node.expression.multiplicative;

import gen.Ardu3kParser;

public class ExponentialNode extends AbstractInfixMultiplicativeNode {

    public ExponentialNode(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "^";
    }

}
