package node.primary;

import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

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

    @Override
    public String toString() {
        return "Double";
    }
}
