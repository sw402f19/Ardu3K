package node.expression.condition;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class EqualNode extends AbstractInfixConditionalNode {

    public EqualNode(Ardu3kParser.InfixEqualExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return " == ";
    }

}
