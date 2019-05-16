package visitor.semantic.reachability;

import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.scope.FunctionNode;
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

    public void visit(ContinueNode node) throws SemanticException {
        RootNode target = findControlTarget(node);
        target.terminatesNormally = false;

    }

    public void visit(BreakNode node) throws SemanticException {
        RootNode target = findControlTarget(node);
        node.terminatesNormally = false;
        if(node.isReachable)
            target.terminatesNormally = true;
    }

    public void visit(ReturnNode node) throws SemanticException {
        RootNode target = findControlTarget(node);
        target.terminatesNormally = false;
    }

    public void visit(DeclarationNode node) {
        node.terminatesNormally = true;
    }

    private RootNode findControlTarget(BreakNode node) throws SemanticException {
        RootNode ptr = node;
        while(ptr.parent != null) {
            if(ptr.parent instanceof AbstractIterativeNode
                    || ptr.parent instanceof SwitchNode)
                return ptr.parent;
            ptr = ptr.parent;
        }
        throw ExceptionFactory.produce("notreachable", node);
    }

    private RootNode findControlTarget(ContinueNode node) throws SemanticException {
        RootNode ptr = node;
        while(ptr.parent != null) {
            if(ptr.parent instanceof AbstractIterativeNode)
                return ptr.parent;
            ptr = ptr.parent;
        }
        throw ExceptionFactory.produce("notreachable", node);
    }

    private RootNode findControlTarget(ReturnNode node) throws SemanticException {
        RootNode ptr = node;
        while(ptr.parent != null) {
            if(ptr.parent instanceof FunctionNode)
                return ptr.parent;
            ptr = ptr.parent;
        }
        throw ExceptionFactory.produce("notreachable", node);
    }
}

