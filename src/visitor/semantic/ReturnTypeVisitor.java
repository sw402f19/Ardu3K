package visitor.semantic;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.VoidNode;
import node.expression.type.BooleanType;
import node.expression.type.ExpressionType;
import node.expression.type.NumeralType;
import node.expression.type.VoidType;
import node.primary.BoolNode;
import node.statement.ReturnNode;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

public class ReturnTypeVisitor extends PrimaryVisitor {

    private ArrayList<RootNode> returnTypes = new ArrayList<>();

    public RootNode visit(ReturnNode node) {
        if(node.getExpression() != null)
            returnTypes.add(new ExpressionTypeVisitor().visit(node.getExpression()));
        return aggregateResult();
    }
    public RootNode aggregateResult() {
        if(returnTypes.size() > 0)
            // todo iterate and cast
            return returnTypes.get(0);
        else
            return new VoidNode();

    }
}
