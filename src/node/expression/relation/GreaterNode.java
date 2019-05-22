package node.expression.relation;

import gen.Ardu3kParser;

public class GreaterNode extends AbstractInfixRelationNode {

    public GreaterNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  " > "  ;
    }

}
