package ASTVisitor.structure;

import gen.Ardu3kParser;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;


public class Parameters extends BaseNode {
    public List<BaseNode> parametersList = new ArrayList<>();

    public Parameters(List<BaseNode> list) {
        this.parametersList = list;
    }

    public Parameters() {
    }
}
