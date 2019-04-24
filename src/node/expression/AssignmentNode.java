package node.expression;

import node.RootNode;
import node.expression.type.ExpressionType;
import node.expression.type.NumeralType;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class AssignmentNode extends AbstractInfixExpressionNode {

    @Override
    public String toString() {
        return "=";
    }

    public AssignmentNode(RootNode parent) {
        super(parent);
    }

    public AssignmentNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AssignmentNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AssignmentNode() {
    }
}
