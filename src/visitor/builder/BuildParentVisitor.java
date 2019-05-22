package visitor.builder;

import exception.factory.SemanticException;
import node.RootNode;
import visitor.BaseASTVisitor;

public class BuildParentVisitor extends BaseASTVisitor<RootNode> {

    public RootNode visit(RootNode node) throws SemanticException {
        for(RootNode n : node.children)
            if(n != null)
                n.parent = node;
        visitChildren(node);
        return node;
    }
}
