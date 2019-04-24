package node.primary;

import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.List;

public class StringNode extends AbstractPrimaryNode {
    public String value;

    public StringNode() {
    }
    public StringNode(String str) {
        value = str;
    }

    public StringNode(Ardu3kParser.StringContext ctx) {
        super(ctx);
        StringBuilder builder = new StringBuilder();
        ctx.string_val().forEach(e -> builder.append(e.getText()));
        value = builder.toString();
    }

    @Override
    public String toString() {
        return "String";
    }
}
