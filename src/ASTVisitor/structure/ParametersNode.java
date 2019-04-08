package ASTVisitor.structure;

import gen.Ardu3kParser;

import java.util.ArrayList;
import java.util.List;


public class ParametersNode extends SelfRecursiveNode<ParameterNode> {

    public ParametersNode() {
    }

    @Override
    public ParameterNode newObject() {
        return new ParameterNode();
    }
}
