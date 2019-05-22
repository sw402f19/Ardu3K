package node.primary;

import org.antlr.v4.runtime.ParserRuleContext;

public class UndefinedNode extends AbstractPrimaryNode {

    public UndefinedNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public UndefinedNode() {
    }
    public UndefinedNode(IdentifierNode node) {
        this.line = node.line;
    }
}
