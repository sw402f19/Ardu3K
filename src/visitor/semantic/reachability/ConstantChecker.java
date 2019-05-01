package visitor.semantic.reachability;

import node.RootNode;
import node.primary.*;
import node.statement.FunctionStmtNode;
import visitor.BaseASTVisitor;

public class ConstantChecker {

    private static boolean value = true;

    public static boolean isConstant(RootNode node) {
        if(node instanceof IdentifierNode)
            return false;
        if(node instanceof FunctionStmtNode)
            return false;

        for(RootNode n : node.children){
            if(n != null)
                value = value && isConstant(n);
        }
        return value;
    }
}
