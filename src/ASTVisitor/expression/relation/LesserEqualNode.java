package ASTVisitor.expression.relation;

import gen.Ardu3kParser;

public class LesserEqualNode extends AbstractInfixRelationNode {

    public LesserEqualNode(){

    }

    public LesserEqualNode(Ardu3kParser.ConditionalEqualExprContext ctx){
        super(ctx);
    }
}
