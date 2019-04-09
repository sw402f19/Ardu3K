package ASTVisitor.primary;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;

public abstract class AbstractPrimaryNode extends RootNode {

    public AbstractPrimaryNode(Ardu3kParser.NumberContext ctx) {
        super(ctx);
    }

    public AbstractPrimaryNode(Ardu3kParser.PrimaryContext ctx){
        super(ctx);
    }

    public AbstractPrimaryNode() {
    }

    public AbstractPrimaryNode(Ardu3kParser.StringContext ctx) {
        super(ctx);
    }

    public AbstractPrimaryNode(Ardu3kParser.IdentifierContext ctx) {
        super(ctx);
    }

    public AbstractPrimaryNode(Ardu3kParser.RelationalExprContext ctx) {
        super(ctx);
    }
}
