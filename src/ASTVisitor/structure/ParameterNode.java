package ASTVisitor.structure;

import ASTVisitor.primary.IdentifierNode;
import gen.Ardu3kParser;

import java.util.ArrayList;

public class ParameterNode extends RootNode {
    public ArrayList<RootNode> children = new ArrayList<>();

    public ParameterNode(RootNode node) {
        children.add(node);
    }
    public ParameterNode(Ardu3kParser.IdentifierContext ctx) {
        children.add(new IdentifierNode(ctx));
    }

    public ParameterNode() {
    }

}
