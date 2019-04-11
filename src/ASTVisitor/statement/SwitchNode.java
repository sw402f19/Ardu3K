package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class SwitchNode extends AbstractStatementNode {

   /* @Override
    public String toString() {
        if (defaultnode != null)
            return "switch "+expression.toString()+" default "+defaultnode.toString();
        else
            return "switch "+expression.toString();
    }*/
    public RootNode getExpression() {
        return children.get(0);
    }
    public void setExpression(RootNode expression) {
        children.set(0, expression);
    }

    public RootNode getDefaultnode() {
        return children.get(1);
    }
    public void setDefaultnode(RootNode defaultnode) {
        children.set(1, defaultnode);
    }
}
