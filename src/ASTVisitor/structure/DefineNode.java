package ASTVisitor.structure;

public class DefineNode extends RootNode {
    public RootNode id;
    public RootNode value;

    @Override
    public String toString() {
        return "Define "+id.toString()+" "+value.toString();
    }
}
