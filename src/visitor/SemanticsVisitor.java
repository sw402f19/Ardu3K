package visitor;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.expression.type.IllegalTypeException;
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
    public RootNode visitChildren(RootNode node){
        RootNode dast = null;

        if(node.children.size() > 0) {
            for (RootNode n : node.children)
                if (n != null) {
                    if(n instanceof AbstractInfixExpressionNode)
                        try {
                            dast = ((AbstractInfixExpressionNode) n.accept(new TypeChecker()));
                        } catch (IllegalTypeException e) {
                            System.out.println(e.getMessage());
                        }
                    else
                        dast = n.accept(this);
                }
        }
        return dast;
    }

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        if(!symbolTable.isPresent(node.getLeft()))
            return visitDeclarationNode(new DeclarationNode(node));
        else {
            //todo check if type compatible
        }

        return node;
    }

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        symbolTable.enterSymbol(node);

        return node;
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
        super.visit(node.getDefinesNode());
        super.visit(node.getFunctionsNode());
        super.visit(node.getSetupNode());
        super.visit(node.getLoopNode());
        return node;
    }
    public RootNode visitSetupNode(SetupNode node) {
        symbolTable.openScope();
        visitBlockNode(node.getBlock());
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
