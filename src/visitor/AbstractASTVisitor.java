package visitor;

import node.structure.RootNode;

public abstract class AbstractASTVisitor<T> implements ASTVisitor<T> {

    public T visitChildren(RootNode node){

        T dast = null;

        if(node.children.size() > 0) {
            for (RootNode n : node.children)
                if (n != null) {
                    dast = n.accept(this);
                }
        }
        return dast;
    }
    public T visit(RootNode node) {
        return node.accept(this);
    }
}
