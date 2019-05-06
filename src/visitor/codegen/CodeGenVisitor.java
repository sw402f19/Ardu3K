package visitor.codegen;

import exception.factory.SemanticException;
import node.primary.BoolNode;
import node.primary.IntegerNode;
import node.primary.StringNode;
import node.scope.*;
import node.statement.control.ForNode;
import visitor.BaseASTVisitor;

public class CodeGenVisitor extends BaseASTVisitor<Void> {

    OutBuilder outBuilder = OutBuilder.getInstance();

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
    public void visit(DefineNode node) throws SemanticException {
        outBuilder.append("#define ");
        visitChildren(node);
        outBuilder.newLine();
    }
    public void visit(IntegerNode node) {
        outBuilder.append(""+node.value);
    }
    public void visit(StringNode node) {
        outBuilder.append("\""+node.value+"\"");
    }
    public void visit(BoolNode node) {
        outBuilder.append(""+node.value);
    }
    public void visit(SetupNode node) throws SemanticException {
        outBuilder.append("void setup()");
        visitChildren(node);
    }
    public void visit(BlockNode node) throws SemanticException {
        outBuilder.append("{\n");
        visitChildren(node);
        outBuilder.append("}\n");
    }
    public void visit(ForNode node) throws SemanticException {
        outBuilder.append("for("+node.getExpression()+" to "+node.getValue()+")");
        visit(node.getStmt());
    }
}
