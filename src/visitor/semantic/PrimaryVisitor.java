package visitor.semantic;

import exception.IllegalParameterTypeException;
import exception.predicate.UndeclaredIdentifierException;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;

import node.RootNode;
import node.primary.AbstractPrimaryNode;
import node.primary.EnclosedExpressionNode;
import node.primary.IdentifierNode;
import node.scope.FunctionNode;
import node.statement.FunctionStmtNode;
import symbol.FunctionSymbol;
import symbol.SymbolTable;
import visitor.BaseASTVisitor;

public class PrimaryVisitor extends BaseASTVisitor<RootNode> {

    protected SymbolTable symbolTable;

    public PrimaryVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
    public RootNode visit(AbstractPrimaryNode node) throws SemanticException {
        return node;
    }
    public RootNode visit(IdentifierNode node) throws SemanticException {
        if(symbolTable.isPresent(node)) {
            return visit(symbolTable.retrieveSymbol(node).getType());
        } else
            throw ExceptionFactory.produce("undeclaredidentifier", node);
    }
    public RootNode visit(EnclosedExpressionNode node) throws SemanticException {
        return visit(node.getExpression());
    }

    public RootNode visit(FunctionStmtNode node) throws SemanticException {
        if(symbolTable.isPresent(node.getId())) {
            FunctionSymbol funcSym = (FunctionSymbol)symbolTable.retrieveSymbol(node.getId());
            return funcSym.getImpl(node).getReturnType();
        } else
            throw ExceptionFactory.produce("undeclaredidentifier", node.getId());
    }
}
