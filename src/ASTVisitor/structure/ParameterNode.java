package ASTVisitor.structure;

public class ParameterNode extends RootNode {
    public RootNode idNode;

    public ParameterNode(RootNode idNode) {
        this.idNode = idNode;
    }
}
