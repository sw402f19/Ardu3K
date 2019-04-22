package node.structure;

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

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitParameterNode(this);
        else return visitor.visitChildren(this);
    }
}
