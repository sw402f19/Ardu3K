package visitor.semantic;

import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixBooleanNode;
import node.expression.AbstractInfixExpressionNode;
import exception.type.IllegalTypeException;
import node.primary.*;
import visitor.semantic.typecast.TypeCaster;

import java.util.ArrayList;

/**
 * Returns the type of a given expression.
 */
public class ExpressionTypeVisitor extends PrimaryVisitor {

    private static ArrayList<Class> types = new ArrayList<>();
    static {
        types.add(StringNode.class);
        types.add(FloatNode.class);
        types.add(IntegerNode.class);
    }

    public RootNode visit(AbstractInfixExpressionNode node) throws SemanticException {
        RootNode leftType = visit(node.getLeft());
        RootNode rightType = visit(node.getRight());
        return highestOrder(leftType, rightType);
    }
    public RootNode visit(AbstractInfixBooleanNode node) {
        BoolNode node1 = new BoolNode();
        node1.setLine(node.line);
        return node1;
    }
    public RootNode visit(ListNode node) throws IllegalTypeException {
        RootNode firstElement = node.getFirstElement();

        if (firstElement instanceof AbstractPrimaryNode){
            visit(firstElement, firstElement);
        } else throw new IllegalTypeException("INVALID first type in list");

        return node;
    }
    // Visitor used to ensure that all elements is the same type as the first element in a ListNode
    public void visit(RootNode element, RootNode firstElement) throws IllegalTypeException {
        if (element != null) {
            if (element.getClass().getSimpleName().equals(firstElement.getClass().getSimpleName())){

                // Recursively run down the list and check if types are compatible
                if (element.children.size() > 0){
                    visit(element.children.get(0), firstElement);
                }

            } else throw new IllegalTypeException("Types in list are not the same!");
        }
    }
    private RootNode highestOrder(RootNode left, RootNode right) throws SemanticException {
        if(types.indexOf(left.getClass()) < types.indexOf(right.getClass()))
            return TypeCaster.cast(right, left);
        else if (types.indexOf(right.getClass()) < types.indexOf(left.getClass()))
            return TypeCaster.cast(left, right);
        return left;
    }
}
