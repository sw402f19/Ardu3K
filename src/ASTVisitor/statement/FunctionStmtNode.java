package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class FunctionStmtNode extends AbstractStatementNode {

    public RootNode id;
    public RootNode arguments;

    @Override
    public String toString() {
        return id.toString()+"( "+arguments.toString()+")";
    }
}
