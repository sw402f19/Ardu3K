package visitor.semantic;

import exception.IllegalTypeException;
import exception.UndeclaredIdentifierException;
import exception.factory.SemanticException;
import node.Node;
import node.RootNode;
import node.primary.AbstractPrimaryNode;
import node.primary.EnclosedExpressionNode;
import node.primary.IdentifierNode;
import node.scope.FunctionNode;
import node.statement.FunctionStmtNode;
import symbol.SymbolTable;
import visitor.BaseASTVisitor;

public class PrimaryVisitor extends BaseASTVisitor<RootNode> {

    SymbolTable symbolTable = SymbolTable.getInstance();

    public RootNode visit(AbstractPrimaryNode node) throws SemanticException {
        return node;
    }
    public RootNode visit(IdentifierNode node) throws UndeclaredIdentifierException {
        if(symbolTable.isPresent(node)) {
            return visit(symbolTable.retrieveSymbol(node).getType());
        } else
            throw new UndeclaredIdentifierException(node.getLine()+" Identifier \""+node.toString()+"\" not declared");
    }
    public RootNode visit(EnclosedExpressionNode node) {
        return visit(node.getExpression());
    }
    public RootNode visit(FunctionStmtNode node) throws UndeclaredIdentifierException {
        if(symbolTable.isPresent(node.getId())) {
            FunctionNode symbolType = ((FunctionNode)symbolTable.retrieveSymbol(node.getId()).getType());
                return visit(symbolType.getReturnType());
        } else
            throw new UndeclaredIdentifierException(node.getLine()+" Identifier \""+node.toString()+"\" not declared");
    }
}
