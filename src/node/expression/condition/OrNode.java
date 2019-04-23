package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class OrNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return "OR";
    }

}
