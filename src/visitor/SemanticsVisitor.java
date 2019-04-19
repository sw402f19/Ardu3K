package visitor;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.primary.IdentifierNode;
import node.statement.ElifNode;
import node.statement.ForNode;
import node.statement.IfNode;
import node.statement.SwitchNode;
import node.structure.*;
import symbol.SymbolTable;

public class SemanticsVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            return visitDeclarationNode(new DeclarationNode(node));
        else {
            if(!visitAbstractExpressionNode((AbstractExpressionNode) node.getRight()).getClass().equals(
                    symbolTable.retrieveSymbol(node).getType()))
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
    public RootNode visitIdentifierNode(IdentifierNode node) {
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

    @Override
    public RootNode visitProgramNode(ProgramNode node) {
        visit(node.getDefinesNode());
        visit(node.getFunctionsNode());
        visit(node.getSetupNode());
        visit(node.getLoopNode());
        return node;
    }
    public RootNode visitSetupNode(SetupNode node) {
        symbolTable.openScope();
        // todo typecheck children
        return node;
    }

    @Override
    public RootNode visitDefinesNode(DefinesNode node) {
        symbolTable.openScope();
        super.visit(node);
        return node;
    }

    @Override
    public RootNode visitDefineNode(DefineNode node) {
        // todo typecheck children
        return node;
    }

    @Override
    public RootNode visitLoopNode(LoopNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitForNode(ForNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitSwitchNode(SwitchNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitIfNode(IfNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitElifNode(ElifNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

}
