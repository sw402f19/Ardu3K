package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DefaultNode extends RootNode {

    @Override
    public String toString() {
        return "default ";
    }
}
