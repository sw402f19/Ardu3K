package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;
import org.antlr.v4.runtime.RuleContext;

public abstract class AbstractStatementNode extends RootNode {
    public AbstractStatementNode() {
    }

    public AbstractStatementNode(RootNode parent) {
        super(parent);
    }

}
