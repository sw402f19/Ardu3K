package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class FunctionStmtNode extends AbstractStatementNode {

    /*@Override
    public String toString() {
        return id.toString()+"( "+arguments.toString()+")";
    }*/

    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode id) {
        children.set(0, id);
    }

    public RootNode getArguments() {
        return children.get(1);
    }
    public void setArguments(RootNode arguments) {
        children.set(1, arguments);
    }
}
