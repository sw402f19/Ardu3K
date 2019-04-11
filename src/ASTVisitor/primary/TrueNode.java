package ASTVisitor.primary;

import gen.Ardu3kParser;

public class TrueNode extends AbstractBoolNode {

    public TrueNode(boolean value) {
        super(value);
    }


    @Override
    public String toString() {
        return ""+value;
    }
}
