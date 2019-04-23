package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ArgumentNode extends RootNode {

    @Override
    public String toString() {
        return "Argument";
    }

}
