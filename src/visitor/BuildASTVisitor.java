package visitor;


import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.additive.*;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.statement.*;
import node.structure.*;
import node.primary.*;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class BuildASTVisitor extends Ardu3kBaseVisitor<RootNode>
{
    @Override
    public RootNode visitProgram(Ardu3kParser.ProgramContext ctx) {
        ProgramNode node = new ProgramNode();
        node.setDefineNode(visitDefines(ctx.define()));
        node.setSetupNode(visitSetup(ctx.setup()));
        node.setLoopNode(visitLoop(ctx.loop()));
        node.setFunctionsNode(visitFunctions(ctx.function()));
        return node;
    }


    public RootNode visitDefines(List<Ardu3kParser.DefineContext> ctx) {
        DefinesNode node = new DefinesNode();
        collectChildren(node, ctx);
        return (node.children.size() > 0 ? node : null);
    }

    @Override
    public RootNode visitDefine(Ardu3kParser.DefineContext ctx) {
        DefineNode node = new DefineNode();
        node.setId(visit(ctx.id));
        node.setValue(visit(ctx.value));
        return node;
    }

    @Override
    public RootNode visitSetup(Ardu3kParser.SetupContext ctx) {
        SetupNode node = new SetupNode();
        node.setBlock(visit(ctx.block()));
        return (node.children.size() > 0 ? node : null);
    }

    @Override
    public RootNode visitLoop(Ardu3kParser.LoopContext ctx) {
        LoopNode node = new LoopNode();
        collectChildren(node, ctx.block().stmt());
        return (node.children.size() > 0 ? node : null);
    }

    public RootNode visitFunctions(List<Ardu3kParser.FunctionContext> ctx) {
        FunctionsNode node = new FunctionsNode();
        collectChildren(node, ctx);
        return (node.children.size() > 0 ? node : null);
    }
    @Override
    public RootNode visitFunction(Ardu3kParser.FunctionContext ctx) {
        FunctionNode node = new FunctionNode();
        node.setId(visit(ctx.identifier()));
        node.setParameter(visit(ctx.parameter()));
        node.setBlock(visit(ctx.block()));
        return (node.children.size() > 0 ? node : null);
    }

    @Override
    public RootNode visitParameter(Ardu3kParser.ParameterContext ctx) {
        ParameterNode node = new ParameterNode();
        return visitParameter(ctx, node);
    }
    private RootNode visitParameter(Ardu3kParser.ParameterContext ctx, ParameterNode node) {
        node.children.add(visit(ctx.id));
        if(ctx.para != null)
            visitParameter(ctx.para, node);
        return node;
    }

    @Override
    public RootNode visitBlock(Ardu3kParser.BlockContext ctx) {
        BlockNode node = new BlockNode();
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override public RootNode visitWhile_stmt(Ardu3kParser.While_stmtContext ctx) {
        WhileNode node = new WhileNode();
        node.setExpressionNode(visitExpression(ctx.expr));
        node.setStmt(visitStmt(ctx.stmt()));
        return node;
    }

    @Override
    public RootNode visitFor_stmt(Ardu3kParser.For_stmtContext ctx) {
        ForNode node = new ForNode();
        node.setExpressionNode(visitExpression(ctx.expr));
        node.setValue(visitNumber(ctx.value));
        node.setStmt(visitStmt(ctx.stmt()));
        return node;
    }

    @Override
    public RootNode visitSwitch_stmt(Ardu3kParser.Switch_stmtContext ctx) {
        SwitchNode node = new SwitchNode();
        node.setExpression(visitExpression(ctx.expression()));
        node.setDefaultNode(visitCase_default(ctx.defaultcase));
        collectChildren(node, ctx.case_stmt());
        return node;
    }

    @Override
    public RootNode visitCase_stmt(Ardu3kParser.Case_stmtContext ctx) {
        CaseNode node = new CaseNode();
        node.expression = visitExpression(ctx.value);
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override
    public RootNode visitCase_default(Ardu3kParser.Case_defaultContext ctx) {
        DefaultNode node = new DefaultNode();
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override
    public RootNode visitIfNoTrailingElse(Ardu3kParser.IfNoTrailingElseContext ctx) {
        IfNode node = new IfNode();
        node.setCondition(visit(ctx.condition));
        node.setUpperbody(visit(ctx.upperbody));
        return node;
    }

    @Override
    public RootNode visitElseTrailingIf(Ardu3kParser.ElseTrailingIfContext ctx) {
        ElifNode node = new ElifNode();
        node.setCondition(visit(ctx.condition));
        node.setUpperbody(visit(ctx.upperbody));
        node.setLowerbody(visit(ctx.lowerbody));
        return node;
    }

    @Override
    public RootNode visitFunction_stmt(Ardu3kParser.Function_stmtContext ctx) {
        FunctionStmtNode node = new FunctionStmtNode();
        node.setId(visit(ctx.id));
        node.setArguments(visit(ctx.args));
        return node;
    }

    @Override
    public RootNode visitArgument(Ardu3kParser.ArgumentContext ctx) {
        ArgumentNode node = new ArgumentNode();
        return visitArgument(ctx, node);
    }

    public RootNode visitArgument(Ardu3kParser.ArgumentContext ctx, ArgumentNode node) {
        node.children.add(visit(ctx.left));
        if(ctx.right != null)
            visitArgument(ctx.right, node);
        return node;
    }

    @Override
    public RootNode visitExpression_stmt(Ardu3kParser.Expression_stmtContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public RootNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        AssignmentNode node = new AssignmentNode();
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitEmptyListAssignment(Ardu3kParser.EmptyListAssignmentContext ctx) {
        ListNode node = new ListNode();
        return node;
    }

    @Override
    public RootNode visitSingleListAssignment(Ardu3kParser.SingleListAssignmentContext ctx) {
        ListNode node = new ListNode();
        node.addElement(visit(ctx.element));
        return node;
    }

    @Override
    public RootNode visitListAssignment(Ardu3kParser.ListAssignmentContext ctx) {
        ListNode node = new ListNode();
        node.addElement(visit(ctx.elements));
        node.addElement(visit(ctx.lastElement));
        return node;
    }

    @Override
    public RootNode visitList_element(Ardu3kParser.List_elementContext ctx) {
        return visit(ctx.element);
    }

    @Override
    public RootNode visitInfixCondtionalOrExpr(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        OrNode node = new OrNode();
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixConditionalAndExpr(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        AndNode node = new AndNode();
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixConditionalXorExpr(Ardu3kParser.InfixConditionalXorExprContext ctx) {
        XorNode node = new XorNode();
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixEqualExpr(Ardu3kParser.InfixEqualExprContext ctx) {
        AbstractInfixExpressionNode node;
        switch (ctx.op.getType()) {
            case Ardu3kParser.EQUALS:
                node = new EqualNode();
                break;
            case Ardu3kParser.NOT:
                node = new NotNode();
                break;

                default:
                    throw new IllegalArgumentException();
        }
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));

        return node;
    }

    @Override
    public RootNode visitInfixRelationalExpr(Ardu3kParser.InfixRelationalExprContext ctx) {
        AbstractInfixRelationNode node;

        switch (ctx.op.getType()) {
            case Ardu3kParser.LESSER:
                node = new LesserNode();
                break;
            case Ardu3kParser.GREATER:
                node = new GreaterNode();
                break;
            case Ardu3kParser.GREATEREQUAL:
                node = new GreaterEqualNode();
                break;
            case Ardu3kParser.LESSEQUAL:
                node = new LesserEqualNode();
                break;
                default:
                    throw new IllegalArgumentException();
        }
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixAdditiveExpr(Ardu3kParser.InfixAdditiveExprContext ctx) {
        AbstractInfixAdditiveNode node;

        switch (ctx.op.getType()) {
            case Ardu3kParser.PLUS:
                node = new PlusNode();
                break;
            case Ardu3kParser.MINUS:
                node = new MinusNode();
                break;

                default:
                    throw new IllegalArgumentException();
        }
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixMultiplicativeExpr(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        AbstractInfixMultiplicativeNode node;

        switch (ctx.op.getType()) {
            case Ardu3kParser.TIMES:
                node = new TimesNode();
                break;
            case Ardu3kParser.DIVIDE:
                node = new DivideNode();
                break;
            case Ardu3kParser.MODULUS:
                node = new ModulusNode();
                break;
            case Ardu3kParser.EXPONENTIAL:
                node = new ExponentialNode();
                break;
            default:
                throw new IllegalArgumentException();
        }

        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitPrimaryLexprR(Ardu3kParser.PrimaryLexprRContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public RootNode visitPrimaryEmptyList(Ardu3kParser.PrimaryEmptyListContext ctx) {
        return new ListNode();
    }

    @Override
    public RootNode visitIdentifier(Ardu3kParser.IdentifierContext ctx) {
        return new IdentifierNode(ctx);
    }

    @Override
    public RootNode visitString(Ardu3kParser.StringContext ctx) {
        StringNode node = new StringNode(ctx.string_val());
        collectChildren(node, ctx.string_val());
        return node;
    }

    @Override
    public RootNode visitString_val(Ardu3kParser.String_valContext ctx) {
        return new StringNode(ctx.getText());
    }

    @Override
    public RootNode visitNumber(Ardu3kParser.NumberContext ctx) {
        AbstractNumberNode node;
        switch (ctx.value.getType()) {
            case Ardu3kParser.INTEGER:
                node = new IntegerNode(ctx);
                break;
            case Ardu3kParser.REAL:
                node = new RealNode(ctx);
                break;
                default:
                    throw new IllegalArgumentException();
        }
        return node;
    }

    @Override
    public RootNode visitBool(Ardu3kParser.BoolContext ctx) {
        return new BoolNode(Boolean.valueOf(ctx.getText()));
    }

    /**
     * Collects children from a given list of type T extending ParserRulerContext to
     * the this node's childen and adds this as parent to its children.
     * @param list to collect from
     * @param <T> type of list to collect from
     */
    public <T extends ParserRuleContext> void collectChildren(RootNode node, List<T> list) {
        if(!list.isEmpty())
            list.forEach(e -> node.children.add(super.visit(e)));
        for(RootNode n : node.children)
            if(n != null)
                n.parent = node;
    }
    /**
     * Collects children from a given list of type T extending ParserRuleContext to the supplied
     * list. Adds this node as parent.
     * @param source to collect from
     * @param target to collect to
     * @param <T> type of list to collect from
     */
    public <T extends ParserRuleContext> void collectChildren(RootNode node, List<T> source, ArrayList<RootNode> target) {
        if(!source.isEmpty())
            source.forEach(e -> target.add(super.visit(e)));
        for(RootNode n : target) {
            if (n != null)
                n.parent = node;
        }
    }

}
