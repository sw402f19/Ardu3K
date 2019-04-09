package ASTVisitor.expression.multiplicative;

public class DivideNode extends AbstractInfixMultiplicativeNode {

    @Override
    public String toString() {
        return left.toString() +" / "+ right.toString();
    }
}
