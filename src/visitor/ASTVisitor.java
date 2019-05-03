package visitor;

import exception.factory.SemanticException;
import node.Node;
import node.RootNode;

public interface ASTVisitor<T> {

    T visitChildren(RootNode node) throws SemanticException;

    T visit(Node node) throws SemanticException;
}
