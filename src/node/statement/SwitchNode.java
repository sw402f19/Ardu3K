package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class SwitchNode extends AbstractStatementNode {

    @Override
    public String toString() {
        return "switch ";
    }
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
    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitSwitchNode(this);
        else return visitor.visitChildren(this);
    }
}
