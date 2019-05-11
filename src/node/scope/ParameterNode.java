package node.scope;

import gen.Ardu3kParser;
import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

public class ParameterNode extends RootNode {

    public ArrayList<RootNode> types = new ArrayList<>();

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
