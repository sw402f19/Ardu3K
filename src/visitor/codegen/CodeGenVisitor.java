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
import visitor.semantic.ExpressionTypeVisitor;

import java.io.*;

@SuppressWarnings("Duplicates")
public class CodeGenVisitor extends BaseASTVisitor<String> {
    private String tab = "    ";
    private int tabLevel = 0;
    private String imports = "";
    private String timers = "";

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
        if(node.getSetupNode() != null) { str += visit(node.getSetupNode()); }
        if(node.getLoopNode() != null) { str += visit(node.getLoopNode()); }
        if(node.getFunctionsNode() != null) { str += visit(node.getFunctionsNode()); }
        if (!(imports.equals(""))) { str = imports + "\n" + str; }
        if (!(timers.equals(""))) { str = "// Timers:\n" + timers + "// ======================\n" + str; }
        str += getCustomArdu3kCode();
        return str;
    }

    private String getCustomArdu3kCode() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/visitor/codegen/Ardu3K_CustomCode.cpp"));
            StringBuilder code = new StringBuilder();
            String str = "";

            while (str != null) {
                code.append(str + "\n");
                str = br.readLine();
            }

            return code.toString();
        } catch (IOException e) { e.printStackTrace(); }
        return "";
    }

    public String visit(ListNode node) throws SemanticException {
        String str;
        str = "{";
        for(RootNode n : node.children) {
            str += visit(n);
            if(!(node.children.indexOf(n) == node.children.size() - 1))
                str += ", ";
        }
        str += "}";
        return str;
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

    public String visit(ExponentialNode node) throws SemanticException {
        if (!(imports.contains("#include <math.h>\n"))){
            imports += "#include <math.h>\n";
        }
        return "pow(" + visit(node.getLeft()) + ", " + visit(node.getRight()) + ")";
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

    public String visit(AbstractUnaryNode node) throws SemanticException {
        return node.getUnarySymbol() + "(" + visit(node.getExpr()) + ")";
    }

    public String visit(AssignmentNode node)  throws SemanticException{
        return tab() + visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
    }

    public String visit(DeclarationNode node) throws SemanticException {
        String str = tab();
        if(node.type instanceof ListNode) {
            str += getPrimaryType(((ListNode) node.type).type);
            str += " " + visit(node.getLeft()) + "[] = " + visit(node.getRight()) + ";";
        } else {
            str += getPrimaryType(node.type);
            str += " " + visit(node.getLeft()) + " = " + visit(node.getRight()) + ";";
        }
        return str;
    }

    public String visit(VoidNode node) {
        return "void";
    }

    public String visit(BoolNode node) {
        return node.value ? "true" : "false";
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
        return "\"" + node.value + "\"";
    }

    public String visit(BlockNode node) throws SemanticException {
        String str = "{\n";
        tabLevel++;
        str += visitChildrenStr(node);
        tabLevel--;
        str += tab() + "}";
        return str;
    }

    public String visit(DefinesNode node) throws SemanticException {
        return visitChildrenStr(node) + "\n";
    }

    public String visit(DefineNode node) throws SemanticException {
        return "#define " + visit(node.getId()) + " " + visit(node.getValue());
    }

    public String visit(FunctionNode node) throws SemanticException {
        String str = "\n" + getPrimaryType(node.getReturnType()) + " " + visit(node.getId());
        str += visit(node.getParameter()) + " " + visit(node.getBlock());
        return str;
    }

    public String visit(FunctionsNode node) throws SemanticException {
        return visitChildrenStr(node);
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
            str += getPrimaryType(node.types.get(i))+" ";
            if (i != node.children.size() - 1){
                str += visit(node.children.get(i)) + ", ";
            } else str += visit(node.children.get(i));
        }

        str += ")";
        return str;
    }

    public String visit(SetupNode node) throws SemanticException {
        return "void setup() " + visit(node.getBlock()) + "\n\n";
    }

    public String visit(ElifNode node) throws SemanticException {
        String str = tab() +  "if (" + visit(node.getExpression()) + ") ";

        if (!(node.getUpperbody()instanceof BlockNode)){
            int prevTabLevel = tabLevel;
            tabLevel = 0;
            str += "{ " + visit(node.getUpperbody()) + " }";
            tabLevel = prevTabLevel;
        } else str += visit(node.getUpperbody());

        str += " else ";

        if (!(node.getLowerbody()instanceof BlockNode)){
            int prevTabLevel = tabLevel;
            tabLevel = 0;
            str += "{ " + visit(node.getLowerbody()) + " }";
            tabLevel = prevTabLevel;
        } else str += visit(node.getLowerbody());

        return str;
    }

    public String visit(ForNode node) throws SemanticException {
        int prevTabLevel = tabLevel;

        String str = tab() + "for (";
        tabLevel = 0;
        if (node.getExpression() instanceof AbstractInfixExpressionNode) {
            AbstractInfixExpressionNode exprNode = (AbstractInfixExpressionNode) node.getExpression();

            str += visit(exprNode) + " " + visit(exprNode.getLeft())+" < ";
            str += visit(node.getValue()) + "; " + visit(exprNode.getLeft()) +  "++) ";
        }

        if (!(node.getStmt() instanceof BlockNode)){
            str += "{ " + visit(node.getStmt()) + " }";
            tabLevel = prevTabLevel;
        } else {
            tabLevel = prevTabLevel;
            str += visit(node.getStmt());
        }

        return str;
    }

    public String visit(IfNode node) throws SemanticException {
        String str = tab() + "if (" + visit(node.getExpression()) + ") ";

        if (!(node.getUpperbody()instanceof BlockNode)){
            int prevTabLevel = tabLevel;
            tabLevel = 0;
            str += "{ " + visit(node.getUpperbody()) + " }";
            tabLevel = prevTabLevel;
        } else str += visit(node.getUpperbody());

        return str;
    }

    public String visit(SwitchNode node) throws SemanticException {
        String str = tab() + "switch (" + visit(node.getExpression()) + ") { \n";
        tabLevel++;
        str += visitChildrenStr(2, node) + visit(node.getDefaultnode());
        tabLevel--;
        return str + tab() + "}";
    }

    public String visit(WhileNode node) throws SemanticException {
        String str = tab() + "while (" + visit(node.getExpression()) + ") ";

        if (!(node.getStmt() instanceof BlockNode)){
            int prevTabLevel = tabLevel;
            tabLevel = 0;
            str += "{ " + visit(node.getStmt()) + " }";
            tabLevel = prevTabLevel;
        } else str += visit(node.getStmt());

        return str;
    }

    public String visit(PinIndexNode node) {
        return node.getIndex() + ", " + node.getbAnalog();
    }

    public String visit(PinReadNode node) throws SemanticException {
        return tab() + "Ardu3K_PinRead(" + visit(node.getPinIndexNode()) + ");";
    }

    public String visit(PinToggleNode node) throws SemanticException {
        return tab() + "Ardu3K_TogglePin(" + visit(node.getPinIndexNode()) + ");";
    }

    public String visit(PinWriteNode node) throws SemanticException {
        return tab() + "Ardu3K_PinWrite(" + visit(node.getPinIndexNode()) + ", " + visit(node.getWriteValue()) + ");";
    }

    public String visit(TimedNode node) throws SemanticException {
        timers += "long " + node.getTimerName() + " = -1;\n";
        return tab() + "Ardu3K_Timed(" + node.getWaitTime() + ", &" + node.getTimerName() + ", " + visit(node.getFuncID()) + ");";
    }

    public String visit(BreakNode node) {
        return tab() + "break;";
    }

    public String visit(ContinueNode node) {
        return tab() + "continue;";
    }

    public String visit(ReturnNode node) throws SemanticException {
        return tab() + "return " + visit(node.getExpression()) + ";";
    }

    public String visit(CaseNode node) throws SemanticException {
        String str = tab() + "case " + visit(node.getExpression()) + ":\n";
        tabLevel++;
        str += visitChildrenStr(1, node) + tab() + "break;";
        tabLevel--;
        return str;
    }

    public String visit(DefaultNode node) throws SemanticException {
        return tab() + "default:\n"+ tab + visitChildrenStr(node);
    }

    public String visit(FunctionStmtNode node) throws SemanticException {
        String str = tab() + visit(node.getId()) + "(";

        if (node.children.size() > 1) {
            for (int i = 0; i < node.getArguments().children.size(); i++) {
                if (i != node.getArguments().children.size() - 1) {
                    str += visit(node.getArguments().children.get(i)) + ", ";
                } else str += visit(node.getArguments().children.get(i));
            }
        }

        return str + ");";
    }

    public String visit(UndefinedNode node) {
        return "UNDEFINED";
    }

    // Used to get the type of the node in C :)
    public String getPrimaryType(RootNode node){
        switch (node.getClass().getSimpleName()){
            case "BoolNode":
                return "bool";
            case "IntegerNode":
                return "int";
            case "StringNode":
                return "String";
            case "FloatNode":
                return "double";
            case "UndefinedNode":
                return "void";
            case "VoidNode":
                return "void";
            default:
                return "UNDEFINED"; // TODO: Add exception here :D
        }
    }

    private String visitChildrenStr(RootNode node) throws SemanticException {
        String str = "";
        for (RootNode n: node.children) { str += visit(n) + "\n"; }
        return str;
    }

    private String visitChildrenStr(int from, RootNode node) throws SemanticException {
        String str = "";
        for (int i = from ; i < node.children.size(); i++) { str += visit(node.children.get(i)) + "\n"; }
        return str;
    }
}