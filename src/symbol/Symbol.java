package symbol;

import node.RootNode;

import java.util.ArrayList;

import static java.util.Objects.hash;

public class Symbol {
    private RootNode name;
    private RootNode type;
    private int depth;


    public Symbol(RootNode name, RootNode type, int depth) {
        this.name = name;
        this.type = type;
        this.depth = depth;
    }


    public RootNode getName() {
        return name;
    }
    public RootNode getType() {
        return type;
    }
    public int getDepth() {
        return depth;
    }

    @Override
    public int hashCode() {
        return hash(name);
    }
}
