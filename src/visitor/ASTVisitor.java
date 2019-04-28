package visitor;

import node.Node;
import node.RootNode;

import java.util.ArrayList;

public interface ASTVisitor<T> {
    ArrayList<RootNode> children = new ArrayList<>();

    T visitChildren(RootNode node);

    T visit(Node node);
}
