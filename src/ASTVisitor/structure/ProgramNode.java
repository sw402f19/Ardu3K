package ASTVisitor.structure;

import java.util.ArrayList;

public class ProgramNode extends RootNode {
    public ArrayList<RootNode> defineNodes = new ArrayList<>();
    public RootNode setupNode;
    public RootNode loopNode;
    public ArrayList<RootNode> functionNodes = new ArrayList<>();

    @Override
    public String toString() {
        return "Program";
    }

    public <T> void accept(ASTVisitor<? extends T> visitor){
        if (visitor instanceof AbstractASTVisitor)
            ((AbstractASTVisitor)visitor).visitProgramNode(this);
        else
            visitor.visitChildren(this);
    }


}
