package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class NotNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return "!=";
    }

}
