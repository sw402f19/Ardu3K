package node.structure;

public class ProgramNode extends RootNode {
    @Override
    public String toString() {
        return "Program";
    }

    public RootNode getDefinesNode() {
        return children.get(0);
    }
    public void setDefineNode(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getSetupNode() {
        return children.get(1);
    }
    public void setSetupNode(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }

    public RootNode getLoopNode() {
        return children.get(2);
    }
    public void setLoopNode(RootNode node) {
        if(children.size() > 2)
            children.set(2, node);
        else
            children.add(node);
    }

    public RootNode getFunctionNode(){
        return children.get(3);
    }
    public void setFunctionNode(RootNode node) {
        if(children.size() > 3)
            children.set(3, node);
        else
            children.add(node);
    }

    /*
    public <T> void accept(visitor<? extends T> visitor){
        if (visitor instanceof AbstractASTVisitor)
            ((AbstractASTVisitor)visitor).visitProgramNode(this);
        else
            visitor.visitChildren(this);
    }*/


}
