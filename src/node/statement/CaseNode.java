package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class CaseNode extends RootNode {
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

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitCaseNode(this);
        else return visitor.visitChildren(this);
    }
}
