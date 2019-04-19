package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class FunctionStmtNode extends RootNode {

    @Override
    public String toString() {
        return "( )";
    }

    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);;
    }

    public RootNode getArguments() {
        return children.get(1);
    }
    public void setArguments(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitFunctionStmtNode(this);
        else return visitor.visitChildren(this);
    }
}
