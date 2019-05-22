package node.expression.additive;

import gen.Ardu3kParser;

public class PlusNode extends AbstractInfixAdditiveNode {

    public PlusNode() {}
    public PlusNode(Ardu3kParser.InfixAdditiveExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  "+" ;
    }

}
