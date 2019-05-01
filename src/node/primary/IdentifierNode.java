package node.primary;

import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import static java.util.Objects.hash;

public class IdentifierNode extends AbstractPrimaryNode {
    protected String value;

    public IdentifierNode(){}

    public IdentifierNode(Ardu3kParser.IdentifierContext ctx){
        super(ctx);
        value = ctx.value.getText();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return ((IdentifierNode) obj).value.equals(value);
    }

    @Override
    public int hashCode() {
        return hash(value);
    }
}
