package ASTVisitor.structure;

import java.util.ArrayList;
import java.util.List;


public class Parameters extends RootNode {
    public List<RootNode> parametersList = new ArrayList<>();

    public Parameters(List<RootNode> list) {
        this.parametersList = list;
    }

    public Parameters() {
    }
}
