package symbol;

import node.RootNode;

import java.util.Stack;

import static java.util.Objects.hash;

public class Symbol {
    private RootNode name;
    private RootNode type;
    private Stack<RootNode> var = new Stack<>();
    private int depth;


    public Symbol(RootNode name, RootNode type, int depth) {
        this.name = name;
        this.type = type;
        this.depth = depth;
    }

    public void push(RootNode var) {
        this.var.push(var);
    }

    @Override
    public int hashCode() {
        return hash(name, type);
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

    public Stack<RootNode> getVar() {
        return var;
    }
}
