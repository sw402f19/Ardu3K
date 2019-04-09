package ASTVisitor.structure;

import java.util.ArrayList;

public class FunctionNode extends RootNode {
    public RootNode id;
    public RootNode parameter;

    @Override
    public String toString() {
        return id + " " +parameter.toString();
    }
}
