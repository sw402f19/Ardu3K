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

import javax.lang.model.type.NullType;
import java.util.ArrayList;

public class FunctionChecker {

    public static void Check(FunctionStmtNode stmtNode, FunctionNode funcNode) throws ArgumentException{
        FunctionParameterArgumentChecker(funcNode, stmtNode);
        FunctionParameterTypeChecker(funcNode, stmtNode);
    }

    public static void Check(FunctionNode node) throws RecursionException {
        CheckForRecursion(node);
    }

    private static void CheckForRecursion(FunctionNode rootNode) throws RecursionException {
        ArrayList<FunctionNode> calledFunctions = new ArrayList<>();

        calledFunctions.add(rootNode);

        RecursionCheck(rootNode, calledFunctions, rootNode.getId().toString() + " (declared on " + rootNode.line + ")");
    }

    private static void RecursionCheck(FunctionNode funcNode, ArrayList<FunctionNode> calledFunctions, String rootInfo) throws RecursionException {
        for (RootNode node: funcNode.getBlock().children){
            if (node instanceof FunctionStmtNode){
                FunctionNode func = (FunctionNode) new SymbolTable().retrieveSymbol(((FunctionStmtNode) node).getId()).getType();

                if (calledFunctions.contains(func)){
                    throw new RecursionException(node.line + " Recursive call to function " + rootInfo);
                } else calledFunctions.add(func);
                RecursionCheck(func, calledFunctions, rootInfo);
            }
        }
    }

    private static void FunctionParameterTypeChecker(FunctionNode funcNode, FunctionStmtNode funcStmtNode) {

        for (RootNode node: funcNode.getBlock().children){
            if (node instanceof AbstractInfixExpressionNode){
                ArrayList<RootNode> leftRight = new ArrayList<>();
                leftRight.add(((AbstractInfixExpressionNode) node).getLeft());
                leftRight.add(((AbstractInfixExpressionNode) node).getRight());

                // Check left and right side
                for (RootNode lrNode: leftRight){
                    leftRight.set(
                            leftRight.indexOf(lrNode),
                            ExprChildCheck(lrNode, funcStmtNode.getArguments().children, funcNode)
                    );
                }

                TypeEvaluator(leftRight.get(0), leftRight.get(1));
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

            return TypeEvaluator(leftRight.get(0), leftRight.get(1));
        } else if (inputNode instanceof IdentifierNode && funcNode.getParameter().children.contains(inputNode)){
            int argIndex = funcNode.getParameter().children.indexOf(inputNode);
            return argTypes.get(argIndex);
        } else return inputNode;
    }

    // Takes in two primary types, and returns which type they will need to resolve to
    // Throws errors if incompatible types
    public static RootNode TypeEvaluator(RootNode left, RootNode right){
        if (left instanceof AbstractPrimaryNode && right instanceof AbstractPrimaryNode){
            switch (left.getClass().getSimpleName()){
                case "BoolNode":
                    if (!(right.getClass().getSimpleName().equals("BoolNode"))) {
                        System.out.println("ERROR: UNABLE TO RESOLVE FROM BOOL TO BOOL!");
                    }
                    break;

                case "IntegerNode":
                    if (right.getClass().getSimpleName().equals("IntegerNode")) { return new IntegerNode(); }
                case "FloatNode":
                    switch (right.getClass().getSimpleName()){
                        case "IntegerNode": case "FloatNode":
                            return new FloatNode();
                        case "StringNode":
                            return new StringNode();
                        default:
                            System.out.println("ERROR: UNABLE TO RESOLVE FROM FLOAT/INT");
                            break;
                    }

                case "StringNode":
                    switch (right.getClass().getSimpleName()){
                        case "IntegerNode": case "FloatNode":
                        case "StringNode":
                            return new StringNode();
                        default:
                            System.out.println("ERROR: UNABLE TO RESOLVE FROM FLOAT/INT");
                            break;
                    }

                default:
                    throw new RuntimeException("ERROR: INVALID TYPES FOR EVALUATOR!");
            }
        } else System.out.println("Tried to evaluate types which are not primary!");
        return new UndefinedNode();
    }

    private static void FunctionParameterArgumentChecker(FunctionNode funcnode, FunctionStmtNode funcStmtNode) throws ArgumentException {
        if (funcnode.getParameter().children.size() < funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too many arguments to function " + funcnode.getId());
        } else if (funcnode.getParameter().children.size() > funcStmtNode.getArguments().children.size()){
            throw new ArgumentException(funcStmtNode.line + " : Too few arguments to function " + funcnode.getId());
        }
    }
}
