package ASTVisitor.structure;

import ASTVisitor.primary.IdentifierNode;
import gen.Ardu3kParser;

public class ParameterNode extends SelfRecursiveNode<ParameterNode> {
    public RootNode idNode;

    public ParameterNode(RootNode idNode) {
        this.idNode = idNode;
    }
    public ParameterNode(Ardu3kParser.IdentifierContext ctx) {
        idNode = new IdentifierNode(ctx);
    }

    public ParameterNode() {
    }

    @Override
    public ParameterNode newObject() {
        return new ParameterNode();
    }
}
