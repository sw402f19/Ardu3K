package node.expression.relation;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class LesserNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return  " < "  ;
    }

}
