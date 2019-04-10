package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class ElifNode extends IfNode {
    public RootNode lowerbody;

    @Override
    public String toString() {
        return "if "+condition.toString()+" do "+upperbody.toString()+" else "+lowerbody.toString();
    }
}
