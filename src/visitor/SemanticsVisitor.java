package visitor;

import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.primary.IdentifierNode;
import symbol.SymbolTable;

public class SemanticsVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            return visitDeclarationNode(new DeclarationNode(node));
        else
            new TypeVisitor().visitAssignmentNode(node);

        return node;
    }

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        symbolTable.enterSymbol(node);
        new TypeVisitor().visitDeclarationNode(node);

        return visitChildren(node);
    }

    // todo temporary error handling
    @Override
    public RootNode visitIdentifier(IdentifierNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            System.out.println("Unknown identifier");
        else
            return node;
        return null;
    }
}
