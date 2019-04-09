package ASTVisitor.primary;

import gen.Ardu3kParser;

public class TrueNode extends AbstractBoolNode {

    public TrueNode(boolean value) {
        super(value);
    }

    public TrueNode(Ardu3kParser.BoolContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
