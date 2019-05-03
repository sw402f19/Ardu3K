package visitor.semantic;

import exception.ArgumentException;
import exception.reachability.RecursionException;
import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.primary.*;
import node.scope.BlockNode;
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

        for (RootNode node: funcNode.getBlock().children){
            System.out.println(node.line + " " + node.getClass().getSimpleName());


            if (node instanceof AbstractInfixExpressionNode){
                ArrayList<RootNode> leftRight = new ArrayList<>();
                leftRight.add(((AbstractInfixExpressionNode) node).getLeft());
                leftRight.add(((AbstractInfixExpressionNode) node).getRight());

                // Check left and right side
                for (RootNode lrNode: leftRight){
                    leftRight.set(leftRight.indexOf(lrNode), ExprChildCheck(lrNode, funcStmtNode.getArguments().children, funcNode));
                }

                // DEBUG
                for (RootNode n: leftRight){
                    System.out.println("---------> " + n.getClass().getSimpleName());
                }


                //TODO IS TYPES COMPATIBLE?

            }
        }
    }

    private static RootNode ExprChildCheck(RootNode inputNode, ArrayList<RootNode> argTypes, FunctionNode funcNode){
        if (inputNode instanceof AbstractInfixExpressionNode){
            ArrayList<RootNode> leftRight = new ArrayList<>();
            leftRight.add(((AbstractInfixExpressionNode) inputNode).getLeft());
            leftRight.add(((AbstractInfixExpressionNode) inputNode).getRight());

            // Check left and right side
            for (RootNode lrNode: leftRight){
                leftRight.set(leftRight.indexOf(lrNode), ExprChildCheck(lrNode, argTypes, funcNode));
            }

            // TODO resolve expression return type

            return leftRight.get(0);



        } else if (inputNode instanceof IdentifierNode && funcNode.getParameter().children.contains(inputNode)){
            int argIndex = funcNode.getParameter().children.indexOf(inputNode);
            return argTypes.get(argIndex);
        } else return inputNode;
    }


    public static void FunctionParameterArgumentChecker(FunctionNode funcnode, FunctionStmtNode funcStmtNode) throws ArgumentException {
        if (funcnode.getParameter().children.size() < funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too many arguments to function " + funcnode.getId());
        } else if (funcnode.getParameter().children.size() > funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too few arguments to function " + funcnode.getId());
        }
    }
}
