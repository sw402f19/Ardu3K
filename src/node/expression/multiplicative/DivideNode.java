package node.expression.multiplicative;

import gen.Ardu3kParser;

public class DivideNode extends AbstractInfixMultiplicativeNode {

    public DivideNode(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "/";
    }

}
