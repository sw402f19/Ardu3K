package node.expression;

import node.RootNode;
import node.structure.FunctionNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;


public class DeclarationNode extends AbstractInfixExpressionNode {

    public DeclarationNode(AbstractInfixExpressionNode node) {
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
    }

    public String getName() {
        return getLeft().toString();
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitDeclarationNode(this);
        else return visitor.visitChildren(this);
    }
}
