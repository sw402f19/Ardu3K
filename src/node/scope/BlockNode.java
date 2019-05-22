package node.scope;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class BlockNode extends RootNode {

    public BlockNode(RootNode parent) {
        super(parent);
    }

    public BlockNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public BlockNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public BlockNode() {
    }

    @Override
    public String toString() {
        return "block";
    }

}
