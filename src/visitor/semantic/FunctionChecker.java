package visitor.semantic;

import exception.IllegalParameterTypeException;
import exception.RecursionException;
import node.RootNode;
import node.primary.BoolNode;
import node.primary.FloatNode;
import node.primary.IntegerNode;
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

    public static void FunctionParameterTypeChecker(FunctionNode funcNode, FunctionStmtNode funcStmtNode) throws Exception {
        System.out.println("Test");
        ArrayList<FunctionNode> Parameters = new ArrayList<>();
        BoolNode test = new BoolNode();
       FloatNode test2 = new FloatNode();

       // funcNode.addParameterType(test);
     //  funcNode.addParameterType(test2);

        for (RootNode node : funcNode.getParameterTypes()){


            ExpressionTypeVisitor test3 = new ExpressionTypeVisitor();
            test3.visit(node);

        }


    }
}
