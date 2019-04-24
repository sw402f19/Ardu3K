package node.expression.relation;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class GreaterNode extends AbstractInfixRelationNode {

    public GreaterNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  " > "  ;
    }

}
