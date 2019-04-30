package visitor.semantic;

import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.additive.AbstractInfixAdditiveNode;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.type.BooleanType;
import exception.IllegalTypeException;
import node.expression.type.NumeralType;
import node.primary.AbstractPrimaryNode;

/**
 * Returns the type of a given expression.
 */
public class ExpressionTypeVisitor extends PrimaryVisitor {

    public RootNode visit(AbstractInfixAdditiveNode node) throws SemanticException {
        isNumeral(node);
        return node;
    }
    public RootNode visit(AbstractInfixConditionalNode node) throws SemanticException {
        isBoolean(node);
        return node;
    }
    public RootNode visit(AbstractInfixMultiplicativeNode node) throws SemanticException {
        isNumeral(node);
        return node;
    }

    public RootNode visit(AbstractInfixRelationNode node) throws SemanticException {
        isNumeral(node);
        return node;
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
    protected void isNumeral(AbstractInfixExpressionNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof NumeralType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().toString()+
                    " for type "+node.toString());
        else if(!(visit(node.getRight()) instanceof NumeralType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().toString()+
                    " for type "+node.toString());
    }
    protected void isBoolean(AbstractInfixExpressionNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof BooleanType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().toString()+
                    " for type "+node.getClass().getSimpleName());
        else if(!(visit(node.getRight()) instanceof BooleanType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().toString()+
                    " for type "+node.toString());
    }
}
