package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class AndNode extends AbstractInfixConditionalNode{

    @Override
    public String toString() {
        return  "AND";
    }

}
