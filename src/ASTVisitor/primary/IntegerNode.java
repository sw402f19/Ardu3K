package ASTVisitor.primary;

import gen.Ardu3kParser;

public class IntegerNode extends AbstractNumberNode {
    public int value;

    public IntegerNode(int value) {
        this.value = value;
    }
    public IntegerNode(Ardu3kParser.NumberContext ctx) {
        this.value = Integer.valueOf(ctx.getText());
    }
    public IntegerNode(String str) {
        this.value = Integer.valueOf(str);
    }
}
