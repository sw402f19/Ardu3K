package node.statement;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ArgumentNode extends RootNode {

    @Override
    public String toString() {
        return "Argument";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitArgumentNode(this);
        else return visitor.visitChildren(this);
    }
}
