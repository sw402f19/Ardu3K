package visitor.semantic;

import exception.DuplicatedParameterIdentifier;
import exception.UndeclaredIdentifierException;
import node.RootNode;
import node.expression.*;
import exception.IllegalTypeException;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.scope.*;
import node.statement.FunctionStmtNode;
import node.statement.control.*;
import symbol.SymbolTable;
import visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SemanticsVisitor extends PrimaryVisitor {

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
    public RootNode visit(ProgramNode node) {
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
    public RootNode visit(IfNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            System.out.println("Expression for If cannot evaluate to boolean got :"+type.toString());
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ElifNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            System.out.println("Expression for If-else cannot evaluate to boolean got :"+type.toString());
        visitChildren(node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(SwitchNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof NumeralType))
            System.out.println("Expression for Switch cannot evaluate to boolean got :"+type.toString());
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
    public RootNode visit(WhileNode node) {
        symbolTable.openScope();
        RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
        if(!(type instanceof BooleanType))
            System.out.println("Expression for While-loop cannot evaluate to boolean got :"+type.toString());
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
                if(!(expectedType.getClass().isInstance(expectedType)))
                    throw new IllegalArgumentException(node.getLine()+" Illegal argument type for "
                            +node.getArguments().children.get(i).toString() +" got "
                            +node.getArguments().children.get(i).toString()+", expected "
                            +expectedType.toString());
            }
        }

        if(function instanceof FunctionNode) {
            for (RootNode n : node.getArguments().children) {

            }
        }
        else throw new UndeclaredIdentifierException("Identifier "+node.getId()+" not declared.");
        return node;
    }
    public RootNode visit(FunctionNode node) {

        symbolTable.enterSymbol(node);
        symbolTable.openScope();
        visit(node.getParameter());
        visit(node.getBlock());
        node.setReturnType(new ReturnTypeVisitor().visit(node.getBlock()));
        symbolTable.closeScope();
        return node;
    }

    
    public RootNode visit(ParameterNode node) {
        symbolTable.openScope();
        if(node.children.size() > 0) {
            Set<String> hs1 = new LinkedHashSet(node.children);
            List<String> al2 = new ArrayList<>(hs1);
            if (node.children.size() > al2.size()) {
                throw new DuplicatedParameterIdentifier(node);
            }
        }
        node.children.forEach(e -> symbolTable.enterSymbol((IdentifierNode) e));
        return node;
    }
}
