package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class BreakNode extends RootNode {

    @Override
    public String toString() { return "BREAK"; }

}
