package visitor.semantic;

import exception.*;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import exception.predicate.DuplicateParameterException;
import exception.predicate.UndeclaredIdentifierException;
import exception.reachability.RecursionException;
import node.RootNode;
import node.expression.*;
import node.expression.additive.PlusNode;
import node.expression.condition.AbstractInfixConditionalNode;
import node.expression.relation.AbstractInfixRelationNode;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.expression.type.StringType;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.scope.*;
import node.statement.CaseNode;
import node.statement.FunctionStmtNode;
import node.statement.control.*;
import symbol.SymbolTable;
import visitor.builder.BuildParentVisitor;
import visitor.semantic.reachability.ReachabilityVisitor;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class SemanticsVisitor extends PrimaryVisitor {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AbstractInfixNumeralNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof NumeralType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getLeft());
        }
        if(!(visit(node.getRight()) instanceof NumeralType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getRight());
        }
        return node;
    }
    public RootNode visit(AbstractInfixRelationNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof NumeralType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getLeft());
        }
        if(!(visit(node.getRight()) instanceof NumeralType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getRight());
        }
        return node;
    }
    public RootNode visit(AbstractInfixConditionalNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof BooleanType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getLeft());
        }
        if(!(visit(node.getRight()) instanceof BooleanType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getRight());
        }
        return node;
    }
    public RootNode visit(PlusNode node) throws SemanticException {
        if(!(visit(node.getLeft()) instanceof NumeralType ||
                visit(node.getLeft()) instanceof StringType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getLeft());
        }
        if(!(visit(node.getRight()) instanceof NumeralType ||
                visit(node.getRight()) instanceof StringType)) {
            throw ExceptionFactory.produce("illegaloperand", node, node.getRight());
        }
        return node;
    }

    public RootNode visit(AbstractDeclAssignNode node)  {
        DeclarationNode node1 = null;
        try {
            if(!(symbolTable.isPresent(node.getLeft())) ||
                    symbolTable.retrieveSymbol(node.getLeft()).getType() instanceof UndefinedNode) {
                node1 = new DeclarationNode(node);
                node1.type = new ExpressionTypeVisitor().visit(node.getRight());
                visit(node1);
            }else {
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

    public RootNode visit(SetupNode node) throws SemanticException {
        visitChildren(node);
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
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
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

    public RootNode visit(FunctionStmtNode node) throws UndeclaredIdentifierException, ArgumentException {
        RootNode function;

        try {
            function = symbolTable.retrieveSymbol(node.getId()).getType();

            if (function instanceof FunctionNode) {

                for (int i = 0; i < node.getArguments().children.size(); i++) {
                    RootNode expectedType =
                            new ExpressionTypeVisitor().visit(node.getArguments().children.get(i));
                    RootNode argType = visit(node.getArguments().children.get(i));
                    if (!(expectedType.getClass().isInstance(argType)))
                        throw ExceptionFactory.produce("illegalargument", argType);
                }

                FunctionChecker.Check(node, (FunctionNode) function);

            } else throw new UndeclaredIdentifierException("Identifier " + node.getId() + " not declared.");
        } catch (SemanticException e) { System.out.println(e.getMessage()); }

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

        FunctionChecker.Check(node);

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
