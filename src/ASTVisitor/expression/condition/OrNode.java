package ASTVisitor.expression.condition;

public class OrNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return left.toString() +" OR "+right.toString();
    }
}
