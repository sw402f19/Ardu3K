package node.primary;

import gen.Ardu3kParser;

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
