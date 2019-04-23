package node.composite;

import gen.Ardu3kParser;
import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

public class ListNode extends RootNode {
    public ListNode(Ardu3kParser.List_assignmentContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "[LIST]"; }

    public void addFirstElement(RootNode node){
        if (children.size() != 0){
            children.add(node);
        } else children.add(node);
    }
    // Throws exception if invalid ID
    public RootNode getFirstElement() {
        try {
            if (children.size() > 1) {
                return children.get(1);
            } else throw new Exception("INVALID index of list value");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
