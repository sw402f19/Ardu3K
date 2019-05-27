package node.expression;

import exception.factory.ExceptionFactory;
import node.RootNode;
import node.primary.IdentifierNode;


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
    private void isLegalIdentifier(IdentifierNode node) {

        switch (node.toString()) {
            case "delay", "A", "do", "setup", "loop",
                    "switch", "case", "default", "OR",
                    "AND", "XOR", "true", "false",
                    "for", "while", "break", "continue",
                    "to", "if", "else", "return", "read",
                    "write", "toggle", "sec", "ms", "min",
                    "before", "after", "in", "reset",
                    "pinMode", "INPUT", "OUTPUT" ->
                    throw ExceptionFactory.produce("illegalidentifier", node);
        }
    }
}
