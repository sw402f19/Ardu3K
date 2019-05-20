package node.primary;

import gen.Ardu3kParser;
import node.expression.type.StringType;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.List;

public class StringNode extends AbstractPrimaryNode implements StringType {
    public String value;

    public StringNode() {
    }
    public StringNode(String str) {
        value = str;
    }
    public StringNode(int i) {
        value = Integer.toString(i);
    }

    public StringNode(Ardu3kParser.StringContext ctx) {
        super(ctx);
        StringBuilder builder = new StringBuilder();
        ctx.string_val().forEach(e -> builder.append(e.getText()));
        value = builder.toString();
    }

    public StringNode(IntegerNode node) {
        this.value = Integer.toString(node.value);
        this.line = node.line;
    }

    public StringNode(FloatNode node) {
        this.value = Double.toString(node.value);
        this.line = node.line;
    }

    @Override
    public String toString() {
        return "String";
    }
}
