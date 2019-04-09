package ASTVisitor.expression.relation;

public class GreaterEqualNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return left.toString() +" >= "+right.toString();
    }
}
