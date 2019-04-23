package visitor;

import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.additive.AbstractInfixAdditiveNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.type.BooleanType;
import node.expression.type.IllegalTypeException;
import node.expression.type.NumeralType;
import node.primary.IdentifierNode;
import symbol.SymbolTable;

public class TypeVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AbstractInfixAdditiveNode node) throws IllegalTypeException{
        isNumeral(node);
        visitChildren(node);
        return node;
    }

    public RootNode visit(AbstractInfixConditionalNode node) throws IllegalTypeException{
        isBoolean(node);
        visitChildren(node);
        return node;
    }
    public RootNode visit(AbstractInfixMultiplicativeNode node) throws IllegalTypeException{
        isNumeral(node);
        visitChildren(node);
        return node;
    }

    public RootNode visit(AbstractInfixRelationNode node) throws IllegalTypeException{
        isNumeral(node);
        visitChildren(node);
        return node;
    }

    public RootNode visit(IdentifierNode node) {
        if(symbolTable.isPresent(node))
            return symbolTable.retrieveSymbol(node).getType();
        else
            return node;
    }
    public void isNumeral(AbstractInfixExpressionNode node) throws IllegalTypeException {
        checkIdentifierType(node);
        if(!(node.getLeft() instanceof NumeralType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        if(!(node.getRight() instanceof NumeralType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
    public void isBoolean(AbstractInfixExpressionNode node) throws IllegalTypeException {
        checkIdentifierType(node);
        if(!(node.getLeft() instanceof BooleanType))
            throw new IllegalTypeException(node.getLeft().getLine()+" Illegal type: "+node.getLeft().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
        if(!(node.getRight() instanceof BooleanType))
            throw new IllegalTypeException(node.getRight().getLine()+" Illegal type: "+node.getRight().getClass().getSimpleName()+
                    " for type "+node.getClass().getSimpleName());
    }
    private void checkIdentifierType(AbstractInfixExpressionNode n) {
        if(n.getLeft() instanceof IdentifierNode)
            n.setLeft(visit((IdentifierNode) n.getLeft()));

        if(n.getRight() instanceof IdentifierNode)
            n.setRight(visit((IdentifierNode) n.getRight()));

    }
}
