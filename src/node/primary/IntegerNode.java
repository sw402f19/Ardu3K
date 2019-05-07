package node.primary;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class IntegerNode extends AbstractNumberNode {
    public int value;

    public IntegerNode() { }
    public IntegerNode(int value) {
        this.value = value;
    }
    public IntegerNode(double value) {
        this.value = (int) value;
    }
    public IntegerNode(Ardu3kParser.NumberContext ctx) {
        super(ctx);
        this.value = Integer.valueOf(ctx.getText());
    }
    public IntegerNode(String str) {
        this.value = Integer.valueOf(str);
    }

    @Override
    public String toString() {
        return "Integer";
    }

    public String getValueStr() { return Integer.toString(value); }

}
