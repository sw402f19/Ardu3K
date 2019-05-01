package visitor.semantic.reachability;

import exception.factory.SemanticException;
import node.statement.control.ElifNode;
import node.statement.control.ForNode;
import node.statement.control.IfNode;
import node.statement.control.WhileNode;
import visitor.BaseASTVisitor;

public class ReachabilityVisitor extends BaseASTVisitor<Void> {

    public void visit(IfNode node) throws SemanticException {
        node.getUpperbody().isReachable = true;
        visitChildren(node);
        node.terminatesNormally = node.getUpperbody().terminatesNormally;
    }
    public void visit(ElifNode node) throws SemanticException {
        node.getUpperbody().isReachable = true;
        node.getLowerbody().isReachable = true;
        visitChildren(node);
        node.terminatesNormally =
                node.getUpperbody().terminatesNormally ||
                node.getLowerbody().terminatesNormally;
    }

    public void visit(WhileNode node) throws SemanticException {
        node.isReachable = true;
        node.terminatesNormally = true;
        boolean conditionVal = ConstantChecker.isConstant(node.getExpression());
        if(conditionVal) {
            node.terminatesNormally = false;
        } else
            node.isReachable = false;
        visit(node.getStmt());
    }
    public void visit(ForNode node) {
        node.terminatesNormally = true;
        node.isReachable = true;
    }
}

