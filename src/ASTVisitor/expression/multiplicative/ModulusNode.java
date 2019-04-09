package ASTVisitor.expression.multiplicative;

public class ModulusNode extends AbstractInfixMultiplicativeNode {

    @Override
    public String toString() {
        return left.toString() +" % "+ right.toString();
    }
}
