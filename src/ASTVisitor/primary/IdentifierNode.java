package ASTVisitor.primary;

import gen.Ardu3kParser;
import gen.Ardu3kParser.IdentifierContext;

public class IdentifierNode extends AbstractPrimaryNode {
    String value;


    public IdentifierNode(Ardu3kParser.IdentifierContext ctx){
        value = ctx.value.getText();
    }

    @Override
    public String toString() {
        return value;
    }
}
