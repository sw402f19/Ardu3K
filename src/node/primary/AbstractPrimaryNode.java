package node.primary;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public abstract class AbstractPrimaryNode extends RootNode {

    public AbstractPrimaryNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractPrimaryNode() {
    }
}
