package node.composite;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ListNode extends RootNode {

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitListNode(this);
        else return visitor.visitChildren(this);
    }
}
