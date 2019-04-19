package visitor;

import node.RootNode;
import node.expression.AbstractExpressionNode;
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
        else {
            if(!visitAbstractExpressionNode((AbstractExpressionNode) node.getRight()).getClass().equals(
                    symbolTable.retrieveSymbol(node).getType())
            )
                //todo temporary error handling
                System.out.println("Imcompatible types");
        }

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
            System.out.println("Identifier not declared");
        else
            return node;
        return null;
    }

    @Override
    public RootNode visitAbstractExpressionNode(AbstractExpressionNode node) {
        return super.visitAbstractExpressionNode(node);
    }

}
