package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class EqualNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return " == ";
    }

}
