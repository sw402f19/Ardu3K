package ASTVisitor.primary;

import gen.Ardu3kParser;

public class FalseNode extends AbstractBoolNode {


    public FalseNode(boolean value) {
        super(value);
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
