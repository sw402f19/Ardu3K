package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class IfNode extends AbstractStatementNode {
    public RootNode condition;
    public RootNode upperbody;

    @Override
    public String toString() {
        return "if "+condition.toString()+" do "+upperbody.toString();
    }
}
