package visitor.semantic;

import exception.ArgumentException;
import exception.reachability.RecursionException;
import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.primary.*;
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

    public static void FunctionParameterTypeChecker(FunctionNode funcNode, FunctionStmtNode funcStmtNode, int index) {
            for (RootNode node : funcNode.getBlock().children){
                if (node instanceof AbstractInfixExpressionNode){
                    RootNode left = ExpressionParameterCheck(((AbstractInfixExpressionNode) node).getLeft(), funcNode, index);
                    RootNode right = ExpressionParameterCheck(((AbstractInfixExpressionNode) node).getRight(), funcNode, index);
                }


            }
    }

    private static RootNode ExpressionParameterCheck(RootNode node, FunctionNode funcNode, int index){
        ExpressionTypeVisitor expVisit = new ExpressionTypeVisitor();
        if (node instanceof IdentifierNode){
            if (funcNode.getParameter().children.contains(node)){
                System.out.println(funcNode.getParameterTypes(index).get(funcNode.getParameter().children.indexOf(node)));
                return funcNode.getParameterTypes(index).get(funcNode.getParameter().children.indexOf(node));
            }
        } else  if (node instanceof AbstractInfixExpressionNode){

                }
       return node;
    }

    public static void FunctionParameterArgumentChecker(FunctionNode funcnode, FunctionStmtNode funcStmtNode) throws ArgumentException {
        if (funcnode.getParameter().children.size() < funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too many arguments to function " + funcnode.getId());
        } else if (funcnode.getParameter().children.size() > funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too few arguments to function " + funcnode.getId());
        }
    }
}
