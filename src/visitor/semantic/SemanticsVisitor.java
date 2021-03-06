package visitor.semantic;

import exception.factory.ExceptionCollector;
import exception.factory.ExceptionFactory;
import exception.factory.FullCollectorException;
import exception.factory.SemanticException;
import exception.predicate.DuplicateParameterException;
import node.RootNode;
import node.expression.AbstractDeclAssignNode;
import node.expression.AbstractInfixNumeralNode;
import node.expression.DeclarationNode;
import node.expression.additive.PlusNode;
import node.expression.condition.AbstractInfixConditionalNode;
import node.expression.relation.AbstractInfixRelationNode;
import node.expression.type.BooleanType;
import node.expression.type.NumeralType;
import node.expression.type.StringType;
import node.expression.unary.UnaryNegateNode;
import node.primary.*;
import node.primary.time.TimeNode;
import node.scope.*;
import node.statement.CaseNode;
import node.statement.DefaultNode;
import node.statement.FunctionStmtNode;
import node.statement.control.ElifNode;
import node.statement.control.IfNode;
import node.statement.control.SwitchNode;
import node.statement.control.WhileNode;
import node.statement.pins.*;
import node.statement.time.AbstractTimeStmtNode;
import node.statement.time.DelayNode;
import node.statement.time.ResetNode;
import node.statement.time.ResetSpecificNode;
import symbol.FunctionSymbol;
import symbol.Symbol;
import symbol.SymbolTable;
import visitor.builder.BuildParentVisitor;
import visitor.semantic.reachability.ReachabilityVisitor;
import visitor.semantic.typecast.TypeCaster;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class SemanticsVisitor extends PrimaryVisitor {
    private ExceptionCollector collector = ExceptionCollector.getInstance();

    public SemanticsVisitor(SymbolTable symbolTable) {
        super(symbolTable);
    }

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

    public RootNode visit(AbstractDeclAssignNode node) throws SemanticException {
        DeclarationNode declNode;
        ArrayList<RootNode> pChildren = node.parent.children;
        try {
            if(!(symbolTable.isPresent(node.getLeft())) ||
                    symbolTable.retrieveSymbol(node.getLeft()).getType() instanceof UndefinedNode) {
                declNode = new DeclarationNode(node);
                if (node.getRight() instanceof PinReadNode){
                    declNode.type = ((PinReadNode) node.getRight()).getPinIndexNode();
                } else declNode.type = new ExpressionTypeVisitor(symbolTable).visit(node.getRight());
                pChildren.set(pChildren.indexOf(node), declNode);
                visit(declNode);
            }else {
                visit(node.getRight());
                new AssignmentVisitor(symbolTable).visit(node);
            }
        } catch (SemanticException e) {
            collector.handleException(e);
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
            if(node.getSetupNode() != null)
                node.setSetupNode(visit(node.getSetupNode()));
            if(node.getLoopNode() != null)
                node.setLoopNode(visit(node.getLoopNode()));
            node.getFunctionsNode().children.clear();
            for(Symbol s : symbolTable.getTable().values())
                if(s instanceof FunctionSymbol)
                    for(FunctionNode fn : ((FunctionSymbol) s).impls)
                        node.getFunctionsNode().children.add(fn);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        return node;
    }

    public RootNode visit(SetupNode node) throws SemanticException {
        visitChildren(node);
        return node;
    }

    public RootNode visit(UnaryNegateNode node) throws SemanticException {
        visitChildren(node);
        return node;
    }

    public RootNode visit(PinIndexNode node) throws SemanticException {
        if(!(visit(node.getIndex()) instanceof IntegerNode))
            throw ExceptionFactory.produce("pindexid", node);
        return node;
    }

    public RootNode visit(PinReadNode node) throws SemanticException {
        visitChildren(node);
        return node;
    }

    public RootNode visit(PinWriteNode node) throws SemanticException {
        visit(node.getPinIndexNode());

        if (node.getWriteValue() instanceof BoolNode) {
            // YAY (Do nothing)
        } else if (node.getWriteValue() instanceof IntegerNode) {
            if (((IntegerNode) node.getWriteValue()).getIntValue() < 0 || ((IntegerNode) node.getWriteValue()).getIntValue() > 255) {
                throw ExceptionFactory.produce("ILLEGALPINWRITE", node);
            }
        } else throw ExceptionFactory.produce("ILLEGALPINWRITE", node);

        return node;
    }

    public RootNode visit(DelayNode node) throws SemanticException {
        visitChildren(node);
        return node;
    }

    public RootNode visit(AbstractTimeStmtNode node) throws SemanticException {
        ExpressionTypeVisitor exprVisitor = new ExpressionTypeVisitor(symbolTable);
        new ReachabilityVisitor().visit(node);
        if(symbolTable.isPresent(node.getClockName())) {
            if (!(symbolTable.retrieveSymbol(node.getClockName()).getType() instanceof AbstractTimeStmtNode)) {
                throw ExceptionFactory.produce("needstimepredicate", symbolTable.retrieveSymbol(node.getClockName()).getType());
            }
        } else symbolTable.enterSymbol((IdentifierNode)node.getClockName(), node);

        if (!(exprVisitor.visit(node.getTime()) instanceof TimeNode) || (exprVisitor.visit(node.getTime()) instanceof IntegerNode)) {
            throw ExceptionFactory.produce("INVALIDTIMETYPE", node);
        }
        visitChildren(node);
        return node;
    }

    public RootNode visit(ResetSpecificNode node) throws SemanticException {
        if(!(symbolTable.isPresent(node.getID()))) {
            throw ExceptionFactory.produce("NOTIMERSPECIFIC", node);
        }
        return node;
    }

    public RootNode visit(PinToggleNode node) throws SemanticException {
        visitChildren(node);
        return node;
    }

    public RootNode visit(AbstractScopeNode node) throws SemanticException {
        symbolTable.openScope();
        try {
            new ReachabilityVisitor().visit(node);
            visitChildren(node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        return node;
    }

    public RootNode visit(IfNode node) throws FullCollectorException {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor(symbolTable).visit(node.getExpression());
            if (!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
            visitChildren(node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ElifNode node) throws SemanticException {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor(symbolTable).visit(node.getExpression());
            if(!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
            visitChildren(node);
        } catch (SemanticException e) {
            if(collector.shouldThrow())
                throw new FullCollectorException();
            else
                collector.appendException(e);
        }
        symbolTable.closeScope();
        return node;
    }

    public RootNode visit(SwitchNode node) throws SemanticException {
        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor(symbolTable).visit(node.getExpression());
            if(!(type instanceof AbstractPrimaryNode))
                System.out.println(node.getLine()+" Expression for Switch cannot evaluate to boolean got :"+type.toString());
            visitChildren(node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        symbolTable.closeScope();
        return node;
    }

    public RootNode visit(CaseNode node) throws SemanticException {

        symbolTable.openScope();
        try {
            RootNode type = new ExpressionTypeVisitor(symbolTable).visit(node.getExpression());
            SwitchNode parent = (SwitchNode) node.parent;
            RootNode parentType = new ExpressionTypeVisitor(symbolTable).visit(parent.getExpression());
            if(!(type.getClass().equals(parentType.getClass())))
                throw ExceptionFactory.produce("incompatibletype", parentType, type);
            visitChildren(node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(DefaultNode node) throws SemanticException {
        symbolTable.openScope();
        try {
            visitChildren(node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        symbolTable.closeScope();
        return node;
    }

    public RootNode visit(WhileNode node) throws SemanticException {
        symbolTable.openScope();
        try {
            visitChildren(node);
            RootNode type = new ExpressionTypeVisitor(symbolTable).visit(node.getExpression());
            if (!(type instanceof BooleanType))
                throw ExceptionFactory.produce("needsbooleanpredicate", node);
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(FunctionStmtNode node) throws SemanticException {
        node.st = symbolTable;
        Symbol funcSym;
        try {
            funcSym = symbolTable.retrieveSymbol(node.getId());
            if(funcSym == null)
                throw ExceptionFactory.produce("undeclaredidentifier", node.getId());
            if(funcSym instanceof FunctionSymbol) {
                if(!((FunctionSymbol) funcSym).containsImpl(node)) {
                    ((FunctionSymbol) funcSym).addImpl(node);
                    FunctionNode impl = ((FunctionSymbol) funcSym).getImpl(node);
                    new SemanticsVisitor(((FunctionSymbol) funcSym).declaredST(node)).visit(impl);
                }
            } else
                throw ExceptionFactory.produce("undeclaredidentifier", node.getId());
        } catch (SemanticException e) {
            collector.handleException(e);
        }
        return node;
    }

    public RootNode visit(ResetNode node) throws SemanticException {

        return node;
    }

    public RootNode visit(FunctionNode node) throws SemanticException {
        visit(node.getParameter());
        visit(node.getBlock());
        // todo functioncheck
        FunctionChecker.Check(node);
        node.setReturnType(new ReturnTypeVisitor(symbolTable).initVisit(node.getBlock()));
        if(node.getReturnType() == null)
            throw ExceptionFactory.produce("noreturn", node);
        symbolTable.closeScope();
        return node;
    }
    public RootNode visit(ParameterNode node) throws DuplicateParameterException {
        symbolTable.openScope();
        int i = 0;
        for(RootNode n : node.children) {
            if(symbolTable.isPresent(n))
                throw new DuplicateParameterException((IdentifierNode)n, symbolTable.retrieveSymbol(n).getType());
            else
                symbolTable.enterSymbol((IdentifierNode) n, node.types.get(i));
            i++;
        }
        return node;
    }
}
