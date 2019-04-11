package ASTVisitor.structure;

public class DefineNode extends RootNode {

    @Override
    public String toString() {
        return "Define";
    }
    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode id) {
        children.set(0, id);
    }
    public RootNode getValue() {
        return children.get(1);
    }
    public void setValue(RootNode value) {
        children.set(1, value);
    }
}
