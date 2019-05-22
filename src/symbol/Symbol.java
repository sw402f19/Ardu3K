package symbol;

import node.RootNode;

public class Symbol {
    private RootNode name;
    private RootNode type;
    private int depth;


    public Symbol(RootNode name, RootNode type, int depth) {
        this.name = name;
        this.type = type;
        this.depth = depth;
    }

    public RootNode getType() {
        return type;
    }
    public int getDepth() {
        return depth;
    }
    public RootNode getName() { return name;}

}
