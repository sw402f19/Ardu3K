package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class CaseNode extends AbstractStatementNode {
    public RootNode expression;

    @Override
    public String toString() {
        return "case "+expression.toString();
    }
}
