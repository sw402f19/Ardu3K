package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;

public class AssignmentNode extends AbstractInfixExpressionNode {

    @Override
    public String toString() {
        return "=";
    }
}
