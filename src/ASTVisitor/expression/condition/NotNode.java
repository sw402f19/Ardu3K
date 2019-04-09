package ASTVisitor.expression.condition;

public class NotNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return left.toString() +" != "+right.toString();
    }
}
