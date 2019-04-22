package visitor;

import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.type.IllegalTypeException;
import symbol.SymbolTable;

public class AssignmentVisitor extends BaseASTVisitor<RootNode> {

    private RootNode expectedType;
    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        expectedType = symbolTable.retrieveSymbol(node.getLeft()).getType();
        RootNode expressionTree = visit(node.getRight());
        if(expressionTree.getClass() != null &&
                isInstanceOf(expressionTree.getClass(), expectedType))
            return node;
        else
            throw new IllegalTypeException(expressionTree.getLine()+" Illegal type for "+node.toString());
    }
    public boolean isInstanceOf(Class clazz, Object obj) {
        return clazz.isInstance(obj);
    }

}
