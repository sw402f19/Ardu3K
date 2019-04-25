package visitor.semantic;

import exception.NotCastableException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.additive.AbstractInfixAdditiveNode;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.type.BooleanType;
import exception.IllegalTypeException;
import node.expression.type.NumeralType;
import node.primary.AbstractPrimaryNode;
import node.primary.EnclosedExpressionNode;
import symbol.SymbolTable;
import visitor.semantic.typecast.TypeCaster;


/**
 * Returns the type of a given expression.
 */
public class ExpressionTypeVisitor extends PrimaryVisitor {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(EnclosedExpressionNode node) {
        return visit(node.getExpression());
    }

    public RootNode visit(AbstractInfixAdditiveNode node) throws IllegalTypeException {
        isNumeral(node);
        return node;
    }
    public RootNode visit(AbstractInfixConditionalNode node) throws IllegalTypeException{
        isBoolean(node);
        return node;
    }
    public RootNode visit(AbstractInfixMultiplicativeNode node) throws IllegalTypeException{
        isNumeral(node);
        return node;
    }

    public RootNode visit(AbstractInfixRelationNode node) throws IllegalTypeException{
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
    // todo should check if able to cast if they are not of the same type.
    protected void isNumeral(AbstractInfixExpressionNode node) throws IllegalTypeException {
        if(!(visit(node.getLeft()) instanceof NumeralType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        else if(!(visit(node.getRight()) instanceof NumeralType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
    protected void isBoolean(AbstractInfixExpressionNode node) throws IllegalTypeException {
        if(!(visit(node.getLeft()) instanceof BooleanType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        else if(!(visit(node.getRight()) instanceof BooleanType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
}
