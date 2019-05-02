package visitor.semantic;

import exception.predicate.DuplicateParameterException;
import exception.reachability.RecursionException;
import exception.predicate.UndeclaredIdentifierException;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.expression.*;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.scope.*;
import node.statement.CaseNode;
import node.statement.FunctionStmtNode;
import node.statement.control.*;
import symbol.SymbolTable;
import visitor.builder.BuildParentVisitor;
import visitor.semantic.reachability.ReachabilityVisitor;
import visitor.semantic.reachability.RecursionChecker;

@SuppressWarnings("Duplicates")
public class SemanticsVisitor extends PrimaryVisitor {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AbstractDeclAssignNode node)  {
        DeclarationNode node1 = null;
        try {
            if(!(symbolTable.isPresent(node.getLeft())) ||
                    symbolTable.retrieveSymbol(node.getLeft()).getType() instanceof UndefinedNode)
                node1 = (DeclarationNode)visit(new DeclarationNode(node));
            else {
                this.visit(node.getRight());
                new AssignmentVisitor().visit(node);
            }
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        if(node1 != null) {
            node1.parent = node.parent;
            node.parent.children.set(node.parent.children.indexOf(node), node1);
        }
        return node;
    }
    public RootNode visit(DeclarationNode node) throws SemanticException {
        symbolTable.enterSymbol(node);
        visit(node.getRight());
        return node;
    }
    public RootNode visit(ProgramNode node) throws SemanticException {
        node = (ProgramNode) new BuildParentVisitor().visit(node);
        try {
            if(node.getDefinesNode() != null)
                node.setDefineNode(visit(node.getDefinesNode()));
            if(node.getFunctionsNode() != null)
                node.setFunctionsNode(visit(node.getFunctionsNode()));
            if(node.getSetupNode() != null)
                node.setSetupNode(visit(node.getSetupNode()));
            if(node.getLoopNode() != null)
                node.setLoopNode(visit(node.getLoopNode()));
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        return node;
    }

    public RootNode visit(DefineNode node) {
        symbolTable.enterSymbol(node);
        return node;
    }
    public RootNode visit(AbstractScopeNode node) {
        symbolTable.openScope();
        try {
            new ReachabilityVisitor().visit(node);
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        return node;
    }
    // todo temp error handling
    public RootNode visit(IfNode node) {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            if (!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ElifNode node)  {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            if(!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    // todo what types should switch accept?
    public RootNode visit(SwitchNode node)  {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            if(!(type instanceof NumeralType))
                System.out.println(node.getLine()+" Expression for Switch cannot evaluate to boolean got :"+type.toString());
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(CaseNode node) {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            SwitchNode parent = (SwitchNode) node.parent;
            RootNode parentType = new ExpressionTypeVisitor().visit(parent.getExpression());
            if(!(type.getClass().equals(parentType.getClass())))
                throw ExceptionFactory.produce("incompatibletype", parentType, type);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ForNode node)  {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            if(!(type instanceof NumeralType))
                System.out.println("Expression for For-loop cannot evaluate to literal got :"+type.toString());
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(WhileNode node) {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor().visit(node.getExpression());
            if (!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
            visitChildren(node);
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(FunctionStmtNode node) {
        try {
            if(!symbolTable.isPresent(node))
                throw ExceptionFactory.produce("undeclaredidentifier",node.getId());
            RootNode function = symbolTable.retrieveSymbol(node.getId()).getType();
            if(function instanceof FunctionNode) {
                for (int i = 0; i < node.getArguments().children.size(); i++) {
                    RootNode expectedType =
                            new ExpressionTypeVisitor().visit(node.getArguments().children.get(i));
                    RootNode argType = visit(node.getArguments().children.get(i));
                    if(!(expectedType.getClass().isInstance(argType)))
                        throw ExceptionFactory.produce("illegalargument", argType);
                }
            } else throw new UndeclaredIdentifierException("Identifier "+node.getId()+ " not declared.");
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        return node;
    }
    public RootNode visit(FunctionNode node) throws RecursionException {
        symbolTable.enterSymbol(node);
        symbolTable.openScope();
        try{
            visit(node.getParameter());
            visit(node.getBlock());
            node.setReturnType(new ReturnTypeVisitor().visit(node.getBlock()));
        } catch (SemanticException e) {
            System.out.println(e.getMessage());
        }
        symbolTable.closeScope();
        RecursionChecker.checkForRecursion(node);
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
