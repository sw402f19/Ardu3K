package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;

public class AssignmentNode extends AbstractExpressionNode {
    public RootNode left;
    public RootNode right;

    @Override
    public String toString() {
        return left.toString() +" = "+ right.toString();
    }
}
