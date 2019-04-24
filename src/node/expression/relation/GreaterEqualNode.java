package node.expression.relation;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class GreaterEqualNode extends AbstractInfixRelationNode {

    public GreaterEqualNode(Ardu3kParser.InfixRelationalExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return ">=";
    }

}
