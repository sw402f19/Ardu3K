package node.expression.relation;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class GreaterNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return  " > "  ;
    }

}
