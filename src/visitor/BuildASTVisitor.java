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
import node.scope.*;
import node.primary.*;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;
import node.statement.BreakNode;
import node.statement.ContinueNode;
import node.statement.control.*;
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
        DefineNode node = new DefineNode(ctx);
        node.setId(visit(ctx.id));
        node.setValue(visit(ctx.value));
        return node;
    }

    @Override
    public RootNode visitSetup(Ardu3kParser.SetupContext ctx) {
        SetupNode node = new SetupNode(ctx);
        node.setBlock(visit(ctx.block()));
        return (node.children.size() > 0 ? node : null);
    }

    @Override
    public RootNode visitLoop(Ardu3kParser.LoopContext ctx) {
        LoopNode node = new LoopNode(ctx);
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
        FunctionNode node = new FunctionNode(ctx);
        node.setId(visit(ctx.identifier()));
        node.setParameter(ctx.para != null ? visit(ctx.para) : new ParameterNode());
        node.setBlock(visit(ctx.block()));
        return (node.children.size() > 0 ? node : null);
    }

    @Override
    public RootNode visitParameter(Ardu3kParser.ParameterContext ctx) {
        ParameterNode node = new ParameterNode(ctx);
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
        BlockNode node = new BlockNode(ctx);
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override public RootNode visitWhile_stmt(Ardu3kParser.While_stmtContext ctx) {
        WhileNode node = new WhileNode(ctx);
        node.setExpression(visitExpression(ctx.expr));
        node.setStmt(visit(ctx.body));
        return node;
    }

    @Override
    public RootNode visitFor_stmt(Ardu3kParser.For_stmtContext ctx) {
        ForNode node = new ForNode(ctx);
        node.setExpression(visitExpression(ctx.expr));
        node.setValue(visitNumber(ctx.value));
        node.setStmt(visit(ctx.body));
        return node;
    }
    @Override
    public RootNode visitSwitch_stmt(Ardu3kParser.Switch_stmtContext ctx) {
        SwitchNode node = new SwitchNode(ctx);
        node.setExpression(visitExpression(ctx.expression()));
        node.setDefaultNode(visitCase_default(ctx.defaultcase));
        collectChildren(node, ctx.case_stmt());
        return node;
    }

    @Override
    public RootNode visitCase_stmt(Ardu3kParser.Case_stmtContext ctx) {
        CaseNode node = new CaseNode(ctx);
        node.expression = visitExpression(ctx.value);
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override
    public RootNode visitCase_default(Ardu3kParser.Case_defaultContext ctx) {
        DefaultNode node = new DefaultNode(ctx);
        collectChildren(node, ctx.stmt());
        return node;
    }

    @Override
    public RootNode visitIfNoTrailingElse(Ardu3kParser.IfNoTrailingElseContext ctx) {
        IfNode node = new IfNode(ctx);
        node.setExpression(visit(ctx.condition));
        node.setUpperbody(visit(ctx.upperbody));
        return node;
    }

    @Override
    public RootNode visitElseTrailingIf(Ardu3kParser.ElseTrailingIfContext ctx) {
        ElifNode node = new ElifNode(ctx);
        node.setExpression(visit(ctx.condition));
        node.setUpperbody(visit(ctx.upperbody));
        node.setLowerbody(visit(ctx.lowerbody));
        return node;
    }

    @Override
    public RootNode visitFunction_stmt(Ardu3kParser.Function_stmtContext ctx) {
        FunctionStmtNode node = new FunctionStmtNode(ctx);
        node.setId(visit(ctx.id));
        if(ctx.args != null)
            node.setArguments(visit(ctx.args));
        return node;
    }

    @Override
    public RootNode visitPinToggle(Ardu3kParser.PinToggleContext ctx) {
        return super.visitPinToggle(ctx);
    }

    @Override
    public RootNode visitPinRead(Ardu3kParser.PinReadContext ctx) {
        return super.visitPinRead(ctx);
    }

    @Override
    public RootNode visitPinWrite(Ardu3kParser.PinWriteContext ctx) {
        return super.visitPinWrite(ctx);
    }

    @Override
    public RootNode visitArgument(Ardu3kParser.ArgumentContext ctx) {
        ArgumentNode node = new ArgumentNode(ctx);
        return visitArgument(ctx, node);
    }

    public RootNode visitArgument(Ardu3kParser.ArgumentContext ctx, ArgumentNode node) {
        node.children.add(visit(ctx.left));
        if(ctx.right != null)
            visitArgument(ctx.right, node);
        return node;
    }
    @Override
    public RootNode visitNotailStatement(Ardu3kParser.NotailStatementContext ctx) {
        switch (ctx.notail.getType()) {
                case Ardu3kParser.RETURN:
                    return new ReturnNode(visit(ctx.expression_stmt()), ctx);
                case Ardu3kParser.BREAK:
                    return new BreakNode(ctx);
                case Ardu3kParser.CONTINUE:
                    return new ContinueNode(ctx);
                    default:
                        return null;
        }
    }

    @Override
    public RootNode visitExpression_stmt(Ardu3kParser.Expression_stmtContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public RootNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        AssignmentNode node = new AssignmentNode(ctx);
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitList_assignment(Ardu3kParser.List_assignmentContext ctx) {
        ListNode node = new ListNode(ctx);
        if(ctx.elements != null)
            return visitList_element(ctx.elements, node);
        return node;
    }

    public RootNode visitList_element(Ardu3kParser.List_elementContext ctx, ListNode node){
        node.children.add(visit(ctx.element));
        if (ctx.next != null){
            visitList_element(ctx.next, node);
        }
        return node;
    }

    @Override
    public RootNode visitInfixCondtionalOrExpr(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        OrNode node = new OrNode(ctx);
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixConditionalAndExpr(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        AndNode node = new AndNode(ctx);
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixConditionalXorExpr(Ardu3kParser.InfixConditionalXorExprContext ctx) {
        XorNode node = new XorNode(ctx);
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
        return node;
    }

    @Override
    public RootNode visitInfixEqualExpr(Ardu3kParser.InfixEqualExprContext ctx) {
        AbstractInfixExpressionNode node;
        switch (ctx.op.getType()) {
            case Ardu3kParser.EQUALS:
                node = new EqualNode(ctx);
                break;
            case Ardu3kParser.NOT:
                node = new NotNode(ctx);
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
                node = new LesserNode(ctx);
                break;
            case Ardu3kParser.GREATER:
                node = new GreaterNode(ctx);
                break;
            case Ardu3kParser.GREATEREQUAL:
                node = new GreaterEqualNode(ctx);
                break;
            case Ardu3kParser.LESSEQUAL:
                node = new LesserEqualNode(ctx);
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
                node = new PlusNode(ctx);
                break;
            case Ardu3kParser.MINUS:
                node = new MinusNode(ctx);
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
                node = new TimesNode(ctx);
                break;
            case Ardu3kParser.DIVIDE:
                node = new DivideNode(ctx);
                break;
            case Ardu3kParser.MODULUS:
                node = new ModulusNode(ctx);
                break;
            case Ardu3kParser.EXPONENTIAL:
                node = new ExponentialNode(ctx);
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
        EnclosedExpressionNode node = new EnclosedExpressionNode(ctx);
        node.setExpression(visit(ctx.expression()));
        return node;
    }

    @Override
    public RootNode visitIdentifier(Ardu3kParser.IdentifierContext ctx) {
        return new IdentifierNode(ctx);
    }

    @Override
    public RootNode visitString(Ardu3kParser.StringContext ctx) {
        StringNode node = new StringNode(ctx);
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
        return new BoolNode(ctx);
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
