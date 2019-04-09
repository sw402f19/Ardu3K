package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.RuleContext;

public abstract class AbstractExpressionNode extends RootNode {

    public AbstractExpressionNode(RuleContext ctx) {
        super(ctx);
    }

    public AbstractExpressionNode(Ardu3kParser.ExpressionContext ctx){
        super(ctx);
    }

    protected AbstractExpressionNode() {
    }
}
