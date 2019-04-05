package ASTVisitor.structure;

import java.util.ArrayList;
import java.util.List;


public class ParametersNode extends RootNode {
    public List<RootNode> parametersList = new ArrayList<>();

    public ParametersNode(List<RootNode> list) {
        this.parametersList = list;
    }

    public ParametersNode() {
    }
}
