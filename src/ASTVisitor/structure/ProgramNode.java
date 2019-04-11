package ASTVisitor.structure;

import java.util.ArrayList;

public class ProgramNode extends RootNode {
    @Override
    public String toString() {
        return "Program";
    }

    public RootNode getDefinesNode() {
        return children.get(0);
    }
    public void setDefineNode(RootNode defineNode) {
        children.set(0, defineNode);
    }

    public RootNode getSetupNode() {
        return children.get(1);
    }
    public void setSetupNode(RootNode setupNode) {
        children.set(1, setupNode);
    }

    public RootNode getLoopNode() {
        return children.get(2);
    }
    public void setLoopNode(RootNode loopNode) {
        children.set(2, loopNode);
    }

    public RootNode getFunctionNode(){
        return children.get(3);
    }
    public void setFunctionNode(RootNode functionNode) {
        children.set(3, functionNode);
    }

    /*
    public <T> void accept(ASTVisitor<? extends T> visitor){
        if (visitor instanceof AbstractASTVisitor)
            ((AbstractASTVisitor)visitor).visitProgramNode(this);
        else
            visitor.visitChildren(this);
    }*/


}
