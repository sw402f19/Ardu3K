package ASTVisitor.primary;

import gen.Ardu3kParser;

public class IdentifierNode extends AbstractPrimaryNode {
    String value;

    public IdentifierNode(String value) {
        this.value = value;
    }
    public IdentifierNode(Ardu3kParser.IdentifierContext ctx) {
        this.value = ctx.getText();
    }
}
