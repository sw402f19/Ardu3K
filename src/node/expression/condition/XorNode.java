package node.expression.condition;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class XorNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return "XOR";
    }

}
