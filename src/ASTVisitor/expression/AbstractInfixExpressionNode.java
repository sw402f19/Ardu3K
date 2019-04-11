package ASTVisitor.expression;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;

public abstract class AbstractInfixExpressionNode extends AbstractExpressionNode {
    public RootNode left;
    public RootNode right;

}
