package node.expression.relation;

import gen.Ardu3kParser;

public class LesserNode extends AbstractInfixRelationNode {

    public LesserNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  " < "  ;
    }

}
