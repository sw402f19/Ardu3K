package node.primary;

import gen.Ardu3kParser;
import node.expression.type.BooleanType;
import org.antlr.v4.runtime.ParserRuleContext;

public class BoolNode extends AbstractPrimaryNode implements BooleanType {
    public boolean value;

    public BoolNode(boolean value) {
        this.value = value;
    }

    public BoolNode() { }

    public BoolNode(ParserRuleContext ctx, boolean value) {
        super(ctx);
        this.value = value;
    }

    public BoolNode(Ardu3kParser.BoolContext ctx) {
        super(ctx);
        this.value = Boolean.valueOf(ctx.value.getText());
    }

    @Override
    public String toString() {
        return "Boolean";
    }

}
