package ASTVisitor.primary;

import gen.Ardu3kParser;

import java.util.Calendar;

public class StringNode extends AbstractPrimaryNode {
    String str;

    public StringNode(String str) {
        this.str = str;
    }
    public StringNode(Ardu3kParser.String_valContext ctx) {
        this.str = ctx.getText();
    }
    public StringNode() {
    }
}
