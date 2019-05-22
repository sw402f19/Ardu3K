package visitor.semantic;

import exception.factory.SemanticException;
import node.RootNode;
import node.expression.VoidNode;
import node.statement.termination.ReturnNode;
import symbol.SymbolTable;

import java.util.ArrayList;

public class ReturnTypeVisitor extends PrimaryVisitor {
    public ReturnTypeVisitor(SymbolTable symbolTable) {
        super(symbolTable);
    }
    private ArrayList<RootNode> returnTypes = new ArrayList<>();

    public RootNode initVisit(RootNode node) throws SemanticException {
        visit(node);
        return aggregateResult();
    }

    public RootNode visit(ReturnNode node) throws SemanticException {
        if(node.getExpression() != null)
            returnTypes.add(new ExpressionTypeVisitor(symbolTable).visit(node.getExpression()));
        return aggregateResult();
    }
    public RootNode aggregateResult() {
        if(returnTypes.size() > 0)
            return returnTypes.get(0);
        else
            return new VoidNode();
    }
}
