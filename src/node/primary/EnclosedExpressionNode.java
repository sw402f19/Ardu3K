package node.primary;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.expression.type.StringType;
import node.expression.type.VoidType;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.semantic.PrimaryVisitor;

public class EnclosedExpressionNode extends AbstractExpressionNode implements NumeralType, BooleanType, StringType, VoidType {
    String str;
    public EnclosedExpressionNode(RootNode parent) {
        super(parent);
    }

    public EnclosedExpressionNode(ParserRuleContext ctx) {
        super(ctx);
        str = ctx.getText();
    }

    public EnclosedExpressionNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public EnclosedExpressionNode() {
    }

    public void setExpression(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }
    public RootNode getExpression() {
        if (children.size() > 0) {
            return children.get(0);
        } else return null;
    }

    @Override
    public String toString() {
        return str;
    }
}
