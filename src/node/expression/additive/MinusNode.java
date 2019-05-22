package node.expression.additive;

import gen.Ardu3kParser;

public class MinusNode extends AbstractInfixAdditiveNode {

    public MinusNode(Ardu3kParser.InfixAdditiveExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  "-" ;
    }

}
