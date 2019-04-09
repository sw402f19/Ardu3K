package ASTVisitor.structure;

import ASTVisitor.primary.IdentifierNode;
import gen.Ardu3kParser;

import java.util.ArrayList;

public class ParameterNode extends RootNode {

    public ParameterNode() {
    }

    @Override
    public String toString() {
        return children.toString();
    }
}
