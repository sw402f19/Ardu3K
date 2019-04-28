package visitor;

import node.Node;
import node.RootNode;

import java.util.ArrayList;

public interface ASTVisitor<T> {

    T visitChildren(RootNode node);

    T visit(Node node);
}
