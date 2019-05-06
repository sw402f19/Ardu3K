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
import visitor.semantic.FunctionChecker;

public class CodeGenVisitor extends BaseASTVisitor<Void> {

    public String visit(ProgramNode node) throws SemanticException {
        String str = "";
        if(node.getDefinesNode() != null) { str += visit(node.getDefinesNode()); }
        if(node.getFunctionsNode() != null) { str += visit(node.getFunctionsNode()); }

        if(node.getSetupNode() != null) { //TODO: Fix bug that causes this not to be a setup node :)
            System.out.println("Type of setup: " + node.getSetupNode().getClass().getSimpleName());
            str += visit(node.getSetupNode());
        } else System.out.println("Setup is empty");

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

    public String visit(AssignmentNode node)  throws SemanticException{
        return visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
    }

    public String visit(DeclarationNode node) throws SemanticException {
        String str = "TYPE"; //TODO: add type
        str += " " + visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
        return str + "\n";
    }

    public String visit(VoidNode node) {
        return "void";
    }

    public String visit(BoolNode node) {
        return node.value ? "1" : "0"; //TODO: Figure out if ArduinoLanguage allows true/false
    }

    public String visit(FloatNode node) {
        return Double.toString(node.value);
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
        return "{\n" + visitChildrenStr(node) +"\n}";
    }

    public String visit(DefinesNode node) throws SemanticException {
        return visitChildrenStr(node) + "\n";
    }

    public String visit(DefineNode node) throws SemanticException {
        return  "#define " + visit(node.getId()) + " " + visit(node.getValue()) + "\n";
    }

    public String visit(FunctionNode node) throws SemanticException {
        String str = getPrimaryType(node.getReturnType()) + " " + visit(node.getId());
        str += visit(node.getParameter()) + " " + visit(node.getBlock());
        return str + "\n";
    }

    public String visit(FunctionsNode node) throws SemanticException {
        return visitChildrenStr(node) + "\n";
    }

    public String visit(LoopNode node) throws SemanticException {
        return "void loop() {\n" + visitChildrenStr(node) + "}\n";
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

    public String visit(SetupNode node) throws SemanticException {
        return "void setup() " + visit(node.getBlock()) + "\n";
    }

    public String visit(ElifNode node) throws SemanticException {
        String str =  "if (" + visit(node.getExpression()) + ") " + visit(node.getUpperbody());
        str += " else " + visit(node.getLowerbody()) + "\n";
        return str;
    }

    public String visit(ForNode node) {
        String str = "";

        // TODO: Add what to write in code here

        return node.toString() + "\n";
    }

    public String visit(IfNode node) throws SemanticException {
        return "if (" + visit(node.getExpression()) + ") " + visit(node.getUpperbody()) + "\n";
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

    public String visit(ReturnNode node) throws SemanticException {
        return"return " + visit(node.getExpression()) + ";";
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

    public String visit(FunctionStmtNode node) throws SemanticException {
        String str = visit(node.getId()) + " (";

        for (int i = 0; i < node.getArguments().children.size(); i++){
            if (i != node.getArguments().children.size() -1){
                str += visit(node.getArguments().children.get(i)) + ", ";
            } else str += visit(node.getArguments().children.get(i));
        }

        return str + ");\n";
    }

    public String visit(UndefinedNode node) {
        return "UNDEFINED";
    }

    // Used to get the type of the node in C :)
    public String getPrimaryType(RootNode node){
        switch (node.getClass().getSimpleName()){
            case "BoolNode": case "IntegerNode": //TODO: Figure out if ArduinoLanguage allows true/false
                return "int";
            case "StringNode":
                return "char[]"; // TODO: Figure out if ArduinoLanguage has support for string
            case "FloatNode":
                return "double";
            case "UndefinedNode":
                return "void";
            default:
                return "UNDEFINED"; // TODO: Add exception here :D
        }
    }

    private String visitChildrenStr(RootNode node) throws SemanticException {
        String str = "";
        for (RootNode n: node.children) { str += visit(n); }
        return str;
    }
}
