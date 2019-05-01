package node.statement.termination;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ContinueNode extends AbstractTerminalNode {

    public ContinueNode(RootNode parent) {
        super(parent);
    }

    public ContinueNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public ContinueNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public ContinueNode() {
    }

    @Override
    public String toString() { return "CONTINUE"; }

}
