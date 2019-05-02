package visitor.semantic.reachability;

import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.statement.control.*;
import node.statement.termination.BreakNode;
import node.statement.termination.ContinueNode;
import node.statement.termination.ReturnNode;
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
    public void visit(ForNode node) throws SemanticException {
        node.terminatesNormally = true;
        node.isReachable = true;
        if(node.getExpression() != null && node.getValue() != null) {
            boolean exprValue = ConstantChecker.isConstant(node.getExpression()) &&
                    ConstantChecker.isConstant(node.getValue());
            if(exprValue)
                node.terminatesNormally = false;
            else
                node.getStmt().terminatesNormally = false;
        }
        else node.terminatesNormally = false;
        visit(node.getStmt());
    }
    public void visit(ContinueNode node) throws SemanticException {
        findControlTarget(node);
    }
    public void visit(BreakNode node) throws SemanticException {
        RootNode target = findControlTarget(node);
        node.terminatesNormally = false;
        if(node.isReachable)
            target.terminatesNormally = true;
    }
    public void visit(ReturnNode node) {

    }
    private RootNode findControlTarget(BreakNode node) throws SemanticException {
        RootNode ptr = node;
        while(ptr.parent != null) {
            if(ptr.parent instanceof AbstractLoopNode
                    || ptr.parent instanceof SwitchNode)
                return ptr.parent;
            ptr = ptr.parent;
        }
        throw ExceptionFactory.produce("notreachable", node);
    }
    private RootNode findControlTarget(ContinueNode node) throws SemanticException {
        RootNode ptr = node;
        while(ptr.parent != null) {
            if(ptr.parent instanceof AbstractLoopNode)
                return ptr.parent;
            ptr = ptr.parent;
        }
        throw ExceptionFactory.produce("notreachable", node);
    }
}

