package ASTVisitor.expression.condition;

public class EqualNode extends AbstractInfixConditionalNode {

    @Override
    public String toString() {
        return left.toString() +" == "+right.toString();
    }
}
