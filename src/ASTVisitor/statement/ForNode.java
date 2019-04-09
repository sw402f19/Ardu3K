package ASTVisitor.statement;

import ASTVisitor.expression.AbstractExpressionNode;
import ASTVisitor.primary.AbstractNumberNode;
import ASTVisitor.structure.RootNode;

public class ForNode extends AbstractStatementNode {

    public RootNode expressionNode;
    public RootNode value;

    @Override
    public String toString() {
        return "for " + expressionNode.toString() + " to" + value + " do ";
    }
}
