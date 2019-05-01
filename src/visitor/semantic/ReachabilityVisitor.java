package visitor.semantic;

import exception.factory.SemanticException;
import node.statement.control.ElifNode;
import node.statement.control.IfNode;
import node.statement.termination.AbstractTerminalNode;
import node.statement.termination.BreakNode;
import visitor.BaseASTVisitor;

public class ReachabilityVisitor extends BaseASTVisitor<Boolean> {

    public boolean visit(AbstractTerminalNode node) {
        return false;
    }

    public boolean visit(IfNode node) throws SemanticException {
        return (visit(node.getUpperbody()));
    }
    public boolean visit(ElifNode node) throws SemanticException {
        return (visit(node.getUpperbody()) || visit(node.getLowerbody()));
    }
}
