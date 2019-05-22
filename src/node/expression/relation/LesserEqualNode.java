package node.expression.relation;


import gen.Ardu3kParser;

public class LesserEqualNode extends AbstractInfixRelationNode {

    public LesserEqualNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  " <= "  ;
    }

}
