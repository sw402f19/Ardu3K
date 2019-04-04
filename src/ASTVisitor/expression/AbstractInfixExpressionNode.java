package ASTVisitor.expression;

import ASTVisitor.structure.BaseNode;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {
    public BaseNode left;
    public BaseNode right;
}
