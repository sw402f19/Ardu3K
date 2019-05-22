package node.expression.condition;

import gen.Ardu3kParser;

public class AndNode extends AbstractInfixConditionalNode{

    public AndNode(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  "AND";
    }

}
