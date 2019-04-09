package ASTVisitor.primary;

import gen.Ardu3kParser;

public abstract class AbstractNumberNode extends AbstractPrimaryNode{

    public AbstractNumberNode(Ardu3kParser.NumberContext ctx){
        super(ctx);
    }


    protected AbstractNumberNode() {
    }

    public AbstractNumberNode(Ardu3kParser.RelationalExprContext ctx) {
        super(ctx);
    }
}
