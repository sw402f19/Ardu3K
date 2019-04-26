package visitor.builder;

import node.Node;
import node.RootNode;
import visitor.BaseASTVisitor;

public class BuildParentVisitor extends BaseASTVisitor<RootNode> {

    public RootNode visit(RootNode node) {
        for(RootNode n : node.children)
            if(n != null)
                n.parent = node;
        visitChildren(node);
        return node;
    }
}
