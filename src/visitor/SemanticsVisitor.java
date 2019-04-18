package visitor;

import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import symbol.SymbolTable;

public class SemanticsVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            return visitDeclarationNode(new DeclarationNode(node));
        else
            node.getRight().accept(new TypeVisitor());

        return node;
    }

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        symbolTable.enterSymbol(node);
        new TypeVisitor().visitDeclarationNode(node);

        return node;
    }
}
