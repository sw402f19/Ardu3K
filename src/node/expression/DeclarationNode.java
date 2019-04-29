package node.expression;

import exception.factory.SemanticException;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;
import visitor.semantic.PrimaryVisitor;


public class DeclarationNode extends AbstractInfixExpressionNode {

    public DeclarationNode(AbstractInfixExpressionNode node) {
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
    }

    public String getName() {
        return getLeft().toString();
    }

}
