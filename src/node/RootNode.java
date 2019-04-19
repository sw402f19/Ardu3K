package node;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

enum TYPE {INT, DOUBLE, STRING, TIME}

@SuppressWarnings (value="unchecked")
public abstract class RootNode implements Node {

    public RootNode parent;
    public ArrayList<RootNode> children = new ArrayList<>();

    public RootNode(RootNode parent) {
        this.parent = parent;
    }

    public RootNode() {
    }

    public abstract <T> T accept(ASTVisitor<? extends T> visitor);

    public void print(int level) {
        for (int i = 1; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(toString());
        for (RootNode child : children) {
            if(child != null)
                child.print(level + 1);
        }
    }

}
