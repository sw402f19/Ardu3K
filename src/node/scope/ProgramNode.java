package node.scope;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

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

    public RootNode getFunctionsNode(){
        return children.get(3);
    }
    public void setFunctionsNode(RootNode node) {
        if(children.size() > 3)
            children.set(3, node);
        else
            children.add(node);
    }


}
