package visitor.codegen;

import exception.factory.SemanticException;
import node.scope.DefinesNode;
import node.scope.ProgramNode;
import visitor.BaseASTVisitor;

public class CodeGenVisitor extends BaseASTVisitor<Void> {

    public void visit(ProgramNode node) throws SemanticException {
        if(node.getDefinesNode() != null)
            visit(node.getDefinesNode());
        if(node.getFunctionsNode() != null)
            visit(node.getFunctionsNode());
        if(node.getSetupNode() != null)
            visit(node.getSetupNode());
        if(node.getLoopNode() != null)
            visit(node.getLoopNode());
    }
    public void visit(DefinesNode node) {

    }
}
