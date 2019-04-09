package ASTVisitor.expression.relation;


public class LesserEqualNode extends AbstractInfixRelationNode {

    @Override
    public String toString() {
        return left.toString() +" <= "+right.toString();
    }
}
