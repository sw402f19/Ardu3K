package ASTVisitor.structure;

import java.util.ArrayList;

public class FunctionNode extends RootNode {
    public RootNode id;
    public RootNode parameter;
    public ArrayList<RootNode> blockStatements = new ArrayList<>();
}
