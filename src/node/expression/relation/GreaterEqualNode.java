package node.expression.relation;

import gen.Ardu3kParser;

public class GreaterEqualNode extends AbstractInfixRelationNode {

    public GreaterEqualNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return ">=";
    }

}
