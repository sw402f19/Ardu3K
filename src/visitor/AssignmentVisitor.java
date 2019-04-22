package visitor;

import node.RootNode;
import node.expression.AssignmentNode;
import symbol.SymbolTable;

public class AssignmentVisitor extends BaseASTVisitor<RootNode> {

    private RootNode expectedType;
    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        expectedType = symbolTable.retrieveSymbol(node.getLeft()).getType();
        visit(node.getRight());
        return node;
    }
}
