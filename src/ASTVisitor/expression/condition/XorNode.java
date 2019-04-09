package ASTVisitor.expression.condition;

public class XorNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return left.toString() +" XOR "+right.toString();
    }
}
