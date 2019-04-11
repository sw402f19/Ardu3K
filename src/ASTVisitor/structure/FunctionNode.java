package ASTVisitor.structure;

import java.util.ArrayList;

public class FunctionNode extends RootNode {
    @Override
    public String toString() {
        return "Function ";
    }

    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode id) {
        children.set(0, id);
    }

    public RootNode getParameter() {
        return children.get(1);
    }
    public void setParameter(RootNode parameter) {
        children.set(1, parameter);
    }

    public RootNode getBlock() {
        return children.get(2);
    }
    public void setBlock(RootNode block) {
        children.set(2, block);
    }


}
