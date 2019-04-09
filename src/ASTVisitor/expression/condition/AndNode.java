package ASTVisitor.expression.condition;

public class AndNode extends AbstractInfixConditionalNode{

    @Override
    public String toString() {
        return left.toString() +" AND "+right.toString();
    }
}
