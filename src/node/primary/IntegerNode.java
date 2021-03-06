package node.primary;

import gen.Ardu3kParser;

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

    public IntegerNode(FloatNode node) {
        this.value = (int) node.value;
        this.line = node.line;
    }

    @Override
    public String toString() {
        return "Integer";
    }

    public String getValueStr() { return Integer.toString(value); }
    public int getIntValue() { return value; }
}
