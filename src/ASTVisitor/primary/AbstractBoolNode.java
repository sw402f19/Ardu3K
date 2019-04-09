package ASTVisitor.primary;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;

public abstract class AbstractBoolNode extends RootNode {
    public boolean value;

    public AbstractBoolNode(Ardu3kParser.BoolContext ctx){
        super(ctx);
    }

    public AbstractBoolNode () { }

    public AbstractBoolNode(boolean value) {
        this.value = value;
    }
}
