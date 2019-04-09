package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class IfElseNode extends AbstractStatementNode {
    public RootNode condition;
    public RootNode upperbody;
    public RootNode lowerbody;
}
