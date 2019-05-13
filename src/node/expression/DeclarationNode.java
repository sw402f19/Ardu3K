package node.expression;

import exception.factory.SemanticException;
import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;
import visitor.semantic.PrimaryVisitor;


public class DeclarationNode extends AbstractDeclAssignNode {

    public RootNode type;

    public DeclarationNode(AbstractInfixExpressionNode node) {
        this.setLeft(node.getLeft());
        this.setRight(node.getRight());
        this.parent = node.parent;
        this.line = node.line;
    }

    public String getName() {
        return getLeft().toString();
    }

}
