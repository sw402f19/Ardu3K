package node.expression.relation;


import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class LesserEqualNode extends AbstractInfixRelationNode {

    public LesserEqualNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  " <= "  ;
    }

}
