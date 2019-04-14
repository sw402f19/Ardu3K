package symbol;

import node.RootNode;

import static java.util.Objects.hash;

public class Symbol {
    private RootNode name;
    private RootNode type;
    private Symbol var;
    private int depth;
    private Symbol hash;


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
    public Symbol getVar() {
        return var;
    }
    public Symbol getHash() {
        return hash;
    }
    public void setVar(Symbol var) {
        this.var = var;
    }
    public void setHash(Symbol hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode() {
        return hash(name);
    }
}
