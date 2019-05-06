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
    private String tab = "    ";
    private int tabLevel = 0;

    // Function for indenting the generated code
    private String tab() {
        String str = "";
        for (int i = 0; i < tabLevel; i ++){
            str += tab;
        }
        return str;
    }

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
        return "LIST"; //TODO: Add our custom code to this
    }

    public String visit(MinusNode node) throws SemanticException {
        return visit(node.getLeft()) + " - " + visit(node.getRight());
    }

    public String visit(PlusNode node) throws SemanticException {
        return visit(node.getLeft()) + " + " + visit(node.getRight());
    }

    public String visit(AndNode node) throws SemanticException {
        return visit(node.getLeft()) + " && " + visit(node.getRight());
    }

    public String visit(EqualNode node) throws SemanticException {
        return visit(node.getLeft()) + " == " + visit(node.getRight());
    }

    public String visit(NotNode node) throws SemanticException {
        return visit(node.getLeft()) + " != " + visit(node.getRight());
    }

    public String visit(OrNode node) throws SemanticException {
        return visit(node.getLeft()) + " || " + visit(node.getRight());
    }

    public String visit(XorNode node) throws SemanticException {
        return visit(node.getLeft()) + " ^ " + visit(node.getRight());
    }

    public String visit(DivideNode node) throws SemanticException {
        return visit(node.getLeft()) + " / " + visit(node.getRight());
    }

    public String visit(ExponentialNode node) {
        return "EXPONENTIAL"; //TODO: Add our custom code to this
    }

    public String visit(ModulusNode node) throws SemanticException {
        return visit(node.getLeft()) + " % " + visit(node.getRight());
    }

    public String visit(TimesNode node) throws SemanticException {
        return visit(node.getLeft()) + " * " + visit(node.getRight());
    }

    public String visit(GreaterEqualNode node) throws SemanticException {
        return visit(node.getLeft()) + " >= " + visit(node.getRight());
    }

    public String visit(GreaterNode node) throws SemanticException {
        return visit(node.getLeft()) + " > " + visit(node.getRight());
    }

    public String visit(LesserEqualNode node)throws SemanticException {
        return visit(node.getLeft()) + " <= " + visit(node.getRight());
    }

    public String visit(LesserNode node) throws SemanticException {
        return visit(node.getLeft()) + " < " + visit(node.getRight());
    }

    public String visit(UnaryNode node) {
        return "UNARY"; //TODO: It seems like ! does not currently work, so we need to fix this :(
    }

    public String visit(AssignmentNode node)  throws SemanticException{
        return tab() + visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
    }

    public String visit(DeclarationNode node) throws SemanticException {
        String str = tab() + "TYPE"; //TODO: add type
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
        return node.value; // TODO: Figure out how to add " on both sides :)
    }

    public String visit(BlockNode node) throws SemanticException {
        String str = "{\n";
        tabLevel++;
        str += visitChildrenStr(node);
        tabLevel--;
        str += "\n}";
        return str;
    }

    public String visit(DefinesNode node) throws SemanticException {
        return visitChildrenStr(node) + "\n";
    }

    public String visit(DefineNode node) throws SemanticException {
        return "#define " + visit(node.getId()) + " " + visit(node.getValue()) + "\n";
    }

    public String visit(FunctionNode node) throws SemanticException {
        String str = "\n" + getPrimaryType(node.getReturnType()) + " " + visit(node.getId());
        str += visit(node.getParameter()) + " " + visit(node.getBlock());
        return str + "\n";
    }

    public String visit(FunctionsNode node) throws SemanticException {
        return visitChildrenStr(node) + "\n";
    }

    public String visit(LoopNode node) throws SemanticException {
        String str = "void loop() {\n";
        tabLevel++;
        str += visitChildrenStr(node) + "}\n";
        tabLevel--;
        return str;
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
        String str = tab() +  "if (" + visit(node.getExpression()) + ") " + visit(node.getUpperbody());
        str += " else " + visit(node.getLowerbody()) + "\n";
        return str;
    }

    public String visit(ForNode node) throws SemanticException {
        String str = tab() + "for (int i = " + visit(node.getExpression()) + "; i < ";
        str += visit(node.getValue()) + "; i++) " + visit(node.getStmt());
        return str + "\n";
    }

    public String visit(IfNode node) throws SemanticException {
        return tab() + "if (" + visit(node.getExpression()) + ") " + visit(node.getUpperbody()) + "\n";
    }

    public String visit(SwitchNode node) throws SemanticException {
        String str = tab() + "switch (" + visit(node.getExpression()) + ") { \n";
        tabLevel++;
        str += visit(node.getDefaultnode());
        tabLevel--;
        return str + "\n"; //TODO: it seems like case noes are not added to a switch node...
    }

    public String visit(WhileNode node) throws SemanticException {
        return tab() + "while (" + visit(node.getExpression()) + ") " + visit(node.getStmt()) + "\n";
    }

    public String visit(PinToggleNode node) {
        return "PIN_TOGGLE"; //TODO: Add our custom code to this
    }

    public String visit(BreakNode node) {
        return tab() + "break;\n";
    }

    public String visit(ContinueNode node) {
        return tab() + "continue;\n";
    }

    public String visit(ReturnNode node) throws SemanticException {
        return tab() + "return " + visit(node.getExpression()) + ";";
    }

    public String visit(ArgumentNode node) {
        return "ARGUMENT"; //TODO: It seems like these are never used...
    }

    public String visit(CaseNode node) {
        return tab() + "CASE" + "\n" + tab + "break;"; //TODO: it seems like case noes are not added to a switch node...
    }

    public String visit(DefaultNode node) throws SemanticException {
        return tab() + "default:\n"+ tab + visitChildrenStr(node);
    }

    public String visit(FunctionStmtNode node) throws SemanticException {
        String str = tab() + visit(node.getId()) + " (";

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
