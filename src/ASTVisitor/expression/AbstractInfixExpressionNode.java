package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {
    public RootNode left;
    public RootNode right;
}
