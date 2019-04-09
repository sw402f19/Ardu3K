package ASTVisitor.primary;

import gen.Ardu3kParser;

import java.util.Calendar;

public class StringNode extends AbstractPrimaryNode {
    public String value;

    public StringNode() {
    }

    public StringNode(Ardu3kParser.StringContext ctx){
        super(ctx);
    }

    @Override
    public String toString() {
        return value;
    }
}
