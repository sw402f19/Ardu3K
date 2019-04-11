package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class CaseNode extends AbstractStatementNode {
    public RootNode expression;

    @Override
    public String toString() {
        return "case ";
    }

    public RootNode getExpression() {
        return children.get(0);
    }
    public void setExpression(RootNode expression) {
        children.set(0, expression);
    }
}
