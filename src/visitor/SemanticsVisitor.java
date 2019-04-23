package visitor;

import node.RootNode;
import node.expression.*;
import node.expression.type.IllegalTypeException;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.statement.*;
import node.scope.*;
import symbol.SymbolTable;

public class SemanticsVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();
    
    public RootNode visit(AssignmentNode node) {
        if(!(symbolTable.isPresent(node.getLeft())) ||
                symbolTable.retrieveSymbol(node.getLeft()).getType() instanceof UndefinedNode)
            return visit(new DeclarationNode(node));
        else {
            try {
                new AssignmentVisitor().visit(node);
            } catch (IllegalTypeException e) {
                System.out.println(e.getMessage());
            }
        }
        return node;
    }
    public RootNode visit(DeclarationNode node) {
        symbolTable.enterSymbol(node);

        return node;
    }
    // todo temporary error handling
    public RootNode visit(IdentifierNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            System.out.println(node.getLine()+" Identifier \""+node.toString()+"\" not declared");
        else
            return node;
        return null;
    }
    public RootNode visit(AbstractInfixExpressionNode node) {
        return new TypeVisitor().visit(node);
    }
    public RootNode visit(ProgramNode node) {
        visit(node.getDefinesNode());
        visit(node.getFunctionsNode());
        visit(node.getSetupNode());
        visit(node.getLoopNode());
        return node;
    }
    public RootNode visit(SetupNode node) {
        symbolTable.openScope();
        visit(node.getBlock());
        return node;
    }

    public RootNode visit(DefinesNode node) {
        symbolTable.openScope();
        visitChildren(node);
        return node;
    }
    public RootNode visit(DefineNode node) {
        symbolTable.enterSymbol(node);
        return node;
    }
    public RootNode visit(LoopNode node) {
        symbolTable.openScope();
        visitChildren(node);
        //symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ForNode node) {
        symbolTable.openScope();
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(SwitchNode node) {
        symbolTable.openScope();
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(IfNode node) {
        symbolTable.openScope();
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(BlockNode node) {
        symbolTable.openScope();
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ElifNode node) {
        symbolTable.openScope();
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(FunctionNode node) {
        symbolTable.enterSymbol(node);
        symbolTable.openScope();
        visit(node.getParameter());
        visit(node.getBlock());
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(FunctionsNode node) {
        symbolTable.openScope();
        return visitChildren(node);
    }
    public RootNode visit(ParameterNode node) {
        symbolTable.openScope();
        node.children.forEach(e -> symbolTable.enterSymbol((IdentifierNode) e));
        return node;
    }
}
