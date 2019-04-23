package visitor;

import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.DivideNode;
import node.expression.multiplicative.ExponentialNode;
import node.expression.multiplicative.ModulusNode;
import node.expression.multiplicative.TimesNode;
import node.expression.relation.GreaterEqualNode;
import node.expression.relation.GreaterNode;
import node.expression.relation.LesserEqualNode;
import node.expression.relation.LesserNode;
import node.expression.type.BooleanType;
import node.expression.type.IllegalTypeException;
import node.expression.type.NumeralType;
import node.primary.AbstractPrimaryNode;
import node.primary.IdentifierNode;
import node.primary.ListElement;
import symbol.SymbolTable;

public class TypeVisitor extends BaseASTVisitor<RootNode> {

    SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitMinusNode(MinusNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitPlusNode(PlusNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitAndNode(AndNode node) {
        isBoolean(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitEqualNode(EqualNode node) {
        isBoolean(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitNotNode(NotNode node) {
        isBoolean(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitOrNode(OrNode node) {
        isBoolean(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitXorNode(XorNode node) {
        isBoolean(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitDivideNode(DivideNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitTimesNode(TimesNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitModulusNode(ModulusNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitExponentialNode(ExponentialNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitGreaterEqualNode(GreaterEqualNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitGreaterNode(GreaterNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitLesserEqualNode(LesserEqualNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }

    @Override
    public RootNode visitLesserNode(LesserNode node) {
        isNumeral(node);
        visit(node);
        return node;
    }
    @Override
    public RootNode visitIdentifierNode(IdentifierNode node) {
        if(symbolTable.isPresent(node))
            return symbolTable.retrieveSymbol(node).getType();
        else
            return node;
    }

    @Override
    public RootNode visitListNode(ListNode node) {
        RootNode firstElement = node.getFirstElement();

        if (firstElement instanceof AbstractPrimaryNode){
            visitListElement(firstElement, firstElement);
        } else throw new IllegalTypeException("INVALID first type in list");

        return node;
    }

    // Visitor used to ensure that all elements is the same type as the first element in a ListNode
    public void visitListElement(RootNode element, RootNode firstElement) {
        if (element != null) {
            if (element.getClass().getSimpleName().equals(firstElement.getClass().getSimpleName())){

                // Recursively run down the list and check if types are compatible
                if (element.children.size() > 0){
                    visitListElement(element.children.get(0), firstElement);
                }

            } else throw new IllegalTypeException("Types in list are not the same!");
        }
    }

    public void isNumeral(AbstractInfixExpressionNode node) {
        if(!(node.getLeft() instanceof NumeralType))
            throw new IllegalTypeException("Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        if(!(node.getRight() instanceof NumeralType))
            throw new IllegalTypeException("Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
    public void isBoolean(AbstractInfixExpressionNode node) {
        if(!(node.getLeft() instanceof BooleanType))
            throw new IllegalTypeException("Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        if(!(node.getRight() instanceof BooleanType))
            throw new IllegalTypeException("Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
}
