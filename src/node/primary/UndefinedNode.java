package node.primary;

import node.RootNode;
import node.structure.ParameterNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class UndefinedNode extends AbstractPrimaryNode {



    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitUndefindedNode(this);
        else return visitor.visitChildren(this);
    }
}
