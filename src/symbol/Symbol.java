package symbol;

import node.RootNode;

import java.util.ArrayList;

import static java.util.Objects.hash;

public class Symbol {
    private RootNode type;
    private int depth;


    public Symbol(RootNode type, int depth) {
        this.type = type;
        this.depth = depth;
    }

    public RootNode getType() {
        return type;
    }
    public int getDepth() {
        return depth;
    }

}
