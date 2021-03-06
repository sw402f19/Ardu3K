package node.primary;

import gen.Ardu3kParser;

public class FloatNode extends AbstractNumberNode {
    public double value;

    public FloatNode(double value) {
        this.value = value;
    }
    public FloatNode(Ardu3kParser.NumberContext ctx) {
        super(ctx);
        this.value = Double.valueOf(ctx.getText());
    }

    public FloatNode(String str) {
        this.value = Double.valueOf(str);
    }

    public FloatNode(){

    }

    public FloatNode(IntegerNode node) {
        this.value = node.value;
        this.line = node.line;
    }

    @Override
    public String toString() {
        return "Double";
    }
}
