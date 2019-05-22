package node.expression.condition;

import gen.Ardu3kParser;

public class XorNode extends AbstractInfixConditionalNode {

    public XorNode(Ardu3kParser.InfixConditionalXorExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "XOR";
    }

}
