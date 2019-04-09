package ASTVisitor.primary;

import gen.Ardu3kParser;

public class FalseNode extends AbstractBoolNode {

    public FalseNode(Ardu3kParser.BoolContext ctx){
        super(ctx);
    }

    public FalseNode(boolean value) {
        super(value);
    }
}
