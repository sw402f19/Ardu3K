package node.expression.multiplicative;

import gen.Ardu3kParser;

public class TimesNode extends AbstractInfixMultiplicativeNode {

    public TimesNode(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "*";
    }

}
