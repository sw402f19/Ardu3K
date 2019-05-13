package visitor.semantic;

import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixBooleanNode;
import node.expression.AbstractInfixExpressionNode;
import exception.type.IllegalTypeException;
import node.primary.*;
import symbol.SymbolTable;
import visitor.semantic.typecast.ExpressionCastVisitor;
import visitor.semantic.typecast.TypeCaster;

import java.util.ArrayList;

/**
 * Returns the type of a given expression.
 */
public class ExpressionTypeVisitor extends PrimaryVisitor {

    public ExpressionTypeVisitor(SymbolTable symbolTable) {
        super(symbolTable);
    }

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
    // todo wip
    public RootNode visit(ListNode node) throws SemanticException {
        RootNode type = node.getFirstElement();
        ExpressionCastVisitor visitor = new ExpressionCastVisitor(symbolTable);

        for(RootNode n : node.children)
            if(!n.getClass().equals(type.getClass()))
                type = visitor.initVisit(n, type);

        if(!(type.getClass().equals(node.getFirstElement().getClass())))
            for(RootNode n : node.children)
                node.children.set(node.children.indexOf(n), TypeCaster.cast(n, type));

        node.type = type;
        return node;
    }
    private RootNode highestOrder(RootNode left, RootNode right) throws SemanticException {
        if(types.indexOf(left.getClass()) < types.indexOf(right.getClass()))
            return TypeCaster.cast(right, left);
        else if (types.indexOf(right.getClass()) < types.indexOf(left.getClass()))
            return TypeCaster.cast(left, right);
        return left;
    }
}
