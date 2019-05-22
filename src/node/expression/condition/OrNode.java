package node.expression.condition;

import gen.Ardu3kParser;

public class OrNode extends AbstractInfixConditionalNode {

    public OrNode(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "OR";
    }

}
