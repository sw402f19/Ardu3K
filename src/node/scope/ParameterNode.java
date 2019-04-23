package node.scope;

import gen.Ardu3kParser;
import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ParameterNode extends RootNode {

    public ParameterNode() {
    }

    public ParameterNode(Ardu3kParser.ParameterContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Parameter";
    }

}
