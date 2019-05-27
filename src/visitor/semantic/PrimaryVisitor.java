package visitor.semantic;

import exception.factory.ExceptionFactory;
import exception.factory.FullCollectorException;
import exception.factory.SemanticException;
import node.RootNode;
import node.primary.AbstractPrimaryNode;
import node.primary.EnclosedExpressionNode;
import node.primary.IdentifierNode;
import node.statement.FunctionStmtNode;
import symbol.FunctionSymbol;
import symbol.Symbol;
import symbol.SymbolTable;
import visitor.BaseASTVisitor;

@SuppressWarnings("Duplicates")
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

    public RootNode visit(FunctionStmtNode node) throws SemanticException, FullCollectorException {
        node.st = symbolTable;
        Symbol funcSym = symbolTable.retrieveSymbol(node.getId());
        if(funcSym == null)
            throw ExceptionFactory.produce("undeclaredidentifier", node.getId());
        if(funcSym instanceof FunctionSymbol) {
            if(!((FunctionSymbol)funcSym).containsImpl(node))
                new SemanticsVisitor(symbolTable).visit(node);
            return ((FunctionSymbol) funcSym).getImpl(node).getReturnType();
        } else
            throw ExceptionFactory.produce("undeclaredidentifier", node.getId());
    }
}
