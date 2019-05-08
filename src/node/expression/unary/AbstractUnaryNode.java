package node.expression.unary;

import node.expression.AbstractExpressionNode;

public class UnaryNode extends AbstractExpressionNode {
    protected char unarySymbol;

    public UnaryNode() { super(); }

    public char getUnarySymbol() { return unarySymbol; }
}
