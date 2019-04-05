package ASTVisitor.primary;

import gen.Ardu3kParser;

public class RealNode extends AbstractNumberNode {
    public double value;

    public RealNode(double value) {
        this.value = value;
    }
    public RealNode(Ardu3kParser.NumberContext ctx) {
        this.value = Double.valueOf(ctx.getText());
    }
    public RealNode(String str) {
        this.value = Double.valueOf(str);
    }
}
