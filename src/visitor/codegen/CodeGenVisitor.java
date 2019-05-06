package visitor.codegen;

import exception.factory.SemanticException;
import node.RootNode;
import node.composite.*;
import node.expression.*;
import node.expression.additive.*;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.unary.*;
import node.primary.*;
import node.scope.*;
import node.statement.*;
import node.statement.control.*;
import node.statement.pins.*;
import node.statement.termination.*;
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
        return "void";
    }

    public String visit(BoolNode node) {
        return node.value ? "1" : "0";
    }

    public String visit(FloatNode node) {
        String str = Double.toString(node.value);
        return str;
    }

    public String visit(IdentifierNode node) {
        return node.toString();
    }

    public String visit(IntegerNode node) {
        return node.getValueStr();
    }

    public String visit(StringNode node) {
        return node.value;
    }

    public String visit(BlockNode node) throws SemanticException {
        String str = "{\n";
        str += visitChildrenStr(node);
        str += "\n}";
        return str;
    }

    public String visit(DefinesNode node) throws SemanticException {
        String str = visitChildrenStr(node);
        return str + "\n";
    }

    public String visit(DefineNode node) throws SemanticException {
        String str = "#define " + visit(node.getId()) + " " + visit(node.getValue());
        return str + "\n";
    }

    public String visit(FunctionNode node) throws SemanticException {
        String str = visit(node.getReturnType()) + " " + visit(node.getId()) + "" + visit(node.getParameter()) + " " + visit(node.getBlock());
        return str + "\n";
    }

    public String visit(FunctionsNode node) throws SemanticException {
        String str = visitChildrenStr(node);
        return str + "\n";
    }

    public String visit(LoopNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(ParameterNode node) throws SemanticException {
        String str = "(" ;

        for (int i = 0; i < node.children.size(); i++){
            if (i != node.children.size() - 1){
                str += visit(node.children.get(i)) + ", ";
            } else str += visit(node.children.get(i));
        }

        str += ")";
        return str;
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

    public String visit(UndefinedNode node) {
        return "UNDEFINED";
    }


    private String visitChildrenStr(RootNode node) throws SemanticException {
        String str = "";
        for (RootNode n: node.children) { str += visit(n); }
        return str;
    }
}
