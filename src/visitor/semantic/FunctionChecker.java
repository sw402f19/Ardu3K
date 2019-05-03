package visitor.semantic;

import exception.RecursionException;
import node.RootNode;
import node.scope.FunctionNode;
import node.statement.FunctionStmtNode;
import symbol.SymbolTable;

import java.util.ArrayList;

public class FunctionChecker {

    private static FunctionChecker thisInstance = new FunctionChecker();

    public static void CheckForRecursion(FunctionNode rootNode) throws RecursionException {
        ArrayList<FunctionNode> calledFunctions = new ArrayList<>();

        calledFunctions.add(rootNode);

        RecursionCheck(rootNode, calledFunctions, rootNode.getId().toString() + " (declared on " + rootNode.line + ")");
    }

    private static void RecursionCheck(FunctionNode funcNode, ArrayList<FunctionNode> calledFunctions, String rootInfo) throws RecursionException {
        for (RootNode node: funcNode.getBlock().children){
            if (node instanceof FunctionStmtNode){
                FunctionNode func = (FunctionNode) SymbolTable.getInstance().retrieveSymbol(((FunctionStmtNode) node).getId()).getType();

                if (calledFunctions.contains(func)){
                    throw new RecursionException(node.line + " Recursive call to function " + rootInfo);
                } else calledFunctions.add(func);

                RecursionCheck(func, calledFunctions, rootInfo);
            }
        }
    }
}
