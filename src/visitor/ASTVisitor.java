package visitor;

import node.Node;
import node.RootNode;

public interface ASTVisitor<T> {

    T visitChildren(RootNode node);

    T visit(Node node);
}
