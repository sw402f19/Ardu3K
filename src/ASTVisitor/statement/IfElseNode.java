package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class IfElseNode extends AbstractStatementNode {
    public RootNode condition;
    public RootNode upperbody;
    public RootNode lowerbody;

    @Override
    public String toString() {
        if(lowerbody != null)
            return "if "+condition.toString()+" do "+upperbody.toString()+" else do "+lowerbody.toString();
        else
            return "if "+condition.toString()+" do "+upperbody.toString();
    }
}
