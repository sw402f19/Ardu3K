package ASTVisitor.expression.additive;

public class MinusNode extends AbstractInfixAdditiveNode {

    @Override
    public String toString() {
        return left.toString() +" - "+ right.toString();
    }
}
