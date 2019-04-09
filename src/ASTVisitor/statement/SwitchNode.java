package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class SwitchNode extends AbstractStatementNode {
    public RootNode expression;
    public RootNode defaultnode;

    @Override
    public String toString() {
        if (defaultnode != null)
            return "switch "+expression.toString()+" default "+defaultnode.toString();
        else
            return "switch "+expression.toString();
    }
}
