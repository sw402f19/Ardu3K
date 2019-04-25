package node.expression;

import node.RootNode;
import node.expression.type.VoidType;

public class VoidNode extends RootNode implements VoidType {

    @Override
    public String toString() {
        return "Void";
    }
}
