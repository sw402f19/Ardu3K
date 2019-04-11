package ASTVisitor.statement;

import ASTVisitor.expression.AbstractExpressionNode;
import ASTVisitor.primary.AbstractNumberNode;
import ASTVisitor.structure.RootNode;

public class ForNode extends AbstractStatementNode {
  /*  @Override
    public String toString() {
        return "for " + expressionNode.toString() + " to" + value + " do ";
    }*/

    public RootNode getExpressionNode() {
        return children.get(0);
    }
    public void setExpressionNode(RootNode expressionNode) {
        children.set(0, expressionNode);
    }

    public RootNode getValue() {
        return children.get(1);
    }
    public void setValue(RootNode value) {
        children.set(1, value);
    }
}
