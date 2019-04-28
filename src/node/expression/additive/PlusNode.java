package node.expression.additive;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class PlusNode extends AbstractInfixAdditiveNode{

    public PlusNode(Ardu3kParser.InfixAdditiveExprContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return  "+" ;
    }

}
