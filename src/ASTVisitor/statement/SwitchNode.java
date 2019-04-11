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
    public void setExpression(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getDefaultnode() {
        return children.get(1);
    }
    public void setDefaultNode(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
