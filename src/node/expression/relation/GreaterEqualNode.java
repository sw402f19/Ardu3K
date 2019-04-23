package node.expression.relation;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class GreaterEqualNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return ">=";
    }

}
