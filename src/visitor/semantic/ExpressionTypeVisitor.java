package visitor.semantic;

import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixBooleanNode;
import node.expression.AbstractInfixExpressionNode;
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
        types.add(ListNode.class);
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

    public RootNode visit(ListNode node) throws SemanticException {
        RootNode type = node.getFirstElement();
        for (RootNode n : node.children)
            if (!n.getClass().equals(type.getClass()))
                type = highestOrder(type, n);

        if (type instanceof ListNode)
            for (RootNode n : node.children)
                ((ListNode) n).type = visit(n);

        return type;
    }

    public RootNode highestOrder(RootNode left, RootNode right) throws SemanticException {
        if (types.indexOf(left.getClass()) < types.indexOf(right.getClass()))
            return TypeCaster.cast(right, left);
        else if (types.indexOf(right.getClass()) < types.indexOf(left.getClass()))
            return TypeCaster.cast(left, right);
        return left;
    }
}
