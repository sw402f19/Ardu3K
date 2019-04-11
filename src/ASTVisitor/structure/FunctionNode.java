package ASTVisitor.structure;

import java.util.ArrayList;

public class FunctionNode extends RootNode {
    public RootNode id;
    public RootNode parameter;
    public RootNode block;

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

  
}
