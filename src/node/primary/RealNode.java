package node.primary;

import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class RealNode extends AbstractNumberNode {
    public double value;

    public RealNode(double value) {
        this.value = value;
    }
    public RealNode(Ardu3kParser.NumberContext ctx) {
        super(ctx);
        this.value = Double.valueOf(ctx.getText());
    }

    public RealNode(String str) {
        this.value = Double.valueOf(str);
    }


    @Override
    public String toString() {
        return ""+value;
    }

}
