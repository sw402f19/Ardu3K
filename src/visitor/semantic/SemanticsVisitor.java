package visitor.semantic;

import exception.DuplicateParameterException;
import exception.RecursionException;
import exception.UndeclaredIdentifierException;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.expression.*;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.scope.*;
import node.statement.FunctionStmtNode;
import node.statement.control.*;
import symbol.SymbolTable;
import visitor.builder.BuildParentVisitor;

public class SemanticsVisitor extends PrimaryVisitor {

    private SymbolTable symbolTable = SymbolTable.getInstance();
    
    public RootNode visit(AssignmentNode node) {
        if(!(symbolTable.isPresent(node.getLeft())) ||
                symbolTable.retrieveSymbol(node.getLeft()).getType() instanceof UndefinedNode)
            return visit(new DeclarationNode(node));
        else {
            this.visit(node.getRight());
            new AssignmentVisitor().visit(node);
        }
        return node;
    }
    public RootNode visit(DeclarationNode node) {
        symbolTable.enterSymbol(node);
        visit(node.getRight());
        return node;
    }
    public RootNode visit(ProgramNode node) {
        node = (ProgramNode) new BuildParentVisitor().visit(node);
        visit(node.getDefinesNode());
        visit(node.getFunctionsNode());
        visit(node.getSetupNode());
        visit(node.getLoopNode());
        return node;
    }

    public RootNode visit(DefineNode node) {
        symbolTable.enterSymbol(node);
        return node;
    }
    public RootNode visit(AbstractScopeNode node) {
        symbolTable.openScope();
        visitChildren(node);
        return node;
    }
    // todo temp error handling
    public RootNode visit(IfNode node) throws SemanticException {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            throw ExceptionFactory.produce("needsbooleanpredicate", node);
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ElifNode node) throws SemanticException {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            throw ExceptionFactory.produce("needsbooleanpredicate", node);
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(SwitchNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof NumeralType))
            System.out.println(node.getLine()+" Expression for Switch cannot evaluate to boolean got :"+type.toString());
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ForNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof NumeralType))
            System.out.println("Expression for For-loop cannot evaluate to literal got :"+type.toString());
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(WhileNode node) throws SemanticException {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            throw ExceptionFactory.produce("needsbooleanpredicate", node);
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(FunctionStmtNode node) throws UndeclaredIdentifierException {
        RootNode function = symbolTable.retrieveSymbol(node.getId()).getType();
        if(function instanceof FunctionNode) {
            for (int i = 0; i < node.getArguments().children.size(); i++) {
                RootNode expectedType =
                        new ExpressionTypeVisitor().visit(node.getArguments().children.get(i));
                // todo here be dragons hehe
                RootNode argType = visit(node.getArguments().children.get(i));
                if(!(expectedType.getClass().isInstance(argType)))
                    throw new IllegalArgumentException(node.getLine()+" Illegal argument type for "
                            +node.getArguments().children.get(i).toString() +" got "
                            +node.getArguments().children.get(i).toString()+", expected "
                            +expectedType.toString());
            }
        } else throw new UndeclaredIdentifierException("Identifier "+node.getId()+ " not declared.");

        return node;
    }
    public RootNode visit(FunctionNode node) throws RecursionException {
        symbolTable.enterSymbol(node);
        symbolTable.openScope();
        visit(node.getParameter());
        visit(node.getBlock());
        node.setReturnType(new ReturnTypeVisitor().visit(node.getBlock()));
        symbolTable.closeScope();
        FunctionChecker.CheckForRecursion(node);
        return node;
    }
    public RootNode visit(ParameterNode node) throws DuplicateParameterException {
        symbolTable.openScope();
        for(RootNode n : node.children) {
            if(symbolTable.isPresent(n))
                throw new DuplicateParameterException((IdentifierNode)n);
            else
                symbolTable.enterSymbol((IdentifierNode) n);
        }
        return node;
    }
}
