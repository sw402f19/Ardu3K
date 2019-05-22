package visitor.semantic.reachability;

import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.primary.IdentifierNode;
import node.statement.FunctionStmtNode;

public class ConstantChecker {

    private static boolean value = true;

    public static boolean isConstant(RootNode node) {
        if(node instanceof AssignmentNode)
            return isConstant(((AssignmentNode) node).getRight());
        if(node instanceof DeclarationNode)
            return isConstant(((DeclarationNode) node).getRight());
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
