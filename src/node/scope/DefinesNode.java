package node.scope;

import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DefinesNode extends AbstractScopeNode {


    @Override
    public String toString() {
        return "Defines";
    }

}
