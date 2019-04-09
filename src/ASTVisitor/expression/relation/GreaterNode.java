package ASTVisitor.expression.relation;

public class GreaterNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return left.toString() +" > "+right.toString();
    }
}
