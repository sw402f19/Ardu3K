package node.composite;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

public class ListNode extends RootNode {
    private ArrayList<RootNode> elements = new ArrayList<>();

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitListNode(this);
        else return visitor.visitChildren(this);
    }

    public void addElement(RootNode node) { elements.add(node); }
    public void setElement (int index, RootNode node) { elements.set(index, node); }
    public RootNode getElement (int index) { return elements.get(index); }
}
