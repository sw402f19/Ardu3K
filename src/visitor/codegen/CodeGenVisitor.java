package visitor.codegen;

import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.expression.VoidNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.DivideNode;
import node.expression.multiplicative.ExponentialNode;
import node.expression.multiplicative.ModulusNode;
import node.expression.multiplicative.TimesNode;
import node.expression.relation.GreaterEqualNode;
import node.expression.relation.GreaterNode;
import node.expression.relation.LesserEqualNode;
import node.expression.relation.LesserNode;
import node.expression.unary.UnaryNode;
import node.primary.*;
import node.scope.*;
import node.statement.ArgumentNode;
import node.statement.CaseNode;
import node.statement.DefaultNode;
import node.statement.FunctionStmtNode;
import node.statement.control.*;
import node.statement.pins.PinToggleNode;
import node.statement.termination.BreakNode;
import node.statement.termination.ContinueNode;
import node.statement.termination.ReturnNode;
import visitor.BaseASTVisitor;

public class CodeGenVisitor extends BaseASTVisitor<Void> {

    public String visit(ProgramNode node) throws SemanticException {
        String str = "";
        if(node.getDefinesNode() != null) { str += visit(node.getDefinesNode()); }
        if(node.getFunctionsNode() != null) { str += visit(node.getFunctionsNode()); }
        if(node.getSetupNode() != null) { str += visit(node.getSetupNode()); }
        if(node.getLoopNode() != null) { str += visit(node.getLoopNode()); }
        return str;
    }

    public String visit(ListNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(MinusNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(PlusNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(AndNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(EqualNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(NotNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(OrNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(XorNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(DivideNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ExponentialNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ModulusNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(TimesNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(GreaterEqualNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(GreaterNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(LesserEqualNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(LesserNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(UnaryNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(AssignmentNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(DeclarationNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(VoidNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(BoolNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(FloatNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(IdentifierNode node) {
        return node.toString();
    }

    public String visit(IntegerNode node) {
        return node.getValueStr();
    }

    public String visit(StringNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(BlockNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(DefinesNode node) throws SemanticException {
        String str = visitChildrenStr(node);
        return str + "\n";
    }

    public String visit(DefineNode node) throws SemanticException {
        String str = "#define " + visit(node.getId()) + " " + visit(node.getValue());
        return str + "\n";
    }

    public String visit(FunctionNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(FunctionsNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(LoopNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ParameterNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(SetupNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ElifNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ForNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(IfNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(SwitchNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(WhileNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(PinToggleNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(BreakNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ContinueNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ReturnNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ArgumentNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(CaseNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(DefaultNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(FunctionStmtNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }


    private String visitChildrenStr(RootNode node) throws SemanticException {
        String str = "";
        for (RootNode n: node.children) { str += visit(n); }
        return str;
    }
}
