package node.scope;

import gen.Ardu3kParser;
import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

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
