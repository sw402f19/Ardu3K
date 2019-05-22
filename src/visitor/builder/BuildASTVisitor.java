package visitor.builder;


import com.rits.cloning.Cloner;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;
import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.additive.AbstractInfixAdditiveNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.unary.UnaryNegateNode;
import node.primary.*;
import node.primary.time.TimeNode;
import node.primary.time.TimeType;
import node.scope.*;
import node.statement.ArgumentNode;
import node.statement.CaseNode;
import node.statement.DefaultNode;
import node.statement.FunctionStmtNode;
import node.statement.control.ElifNode;
import node.statement.control.IfNode;
import node.statement.control.SwitchNode;
import node.statement.control.WhileNode;
import node.statement.pins.*;
import node.statement.termination.BreakNode;
import node.statement.termination.ContinueNode;
import node.statement.termination.ReturnNode;
import node.statement.time.*;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol.FunctionSymbol;
import symbol.Symbol;
import symbol.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class BuildASTVisitor extends Ardu3kBaseVisitor<RootNode>
{
    private SymbolTable symbolTable;

    public BuildASTVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

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
        return node;
    }

    @Override
    public RootNode visitDefine(Ardu3kParser.DefineContext ctx) {
        DefineNode node = new DefineNode(ctx);
        node.setId(visit(ctx.id));
        node.setValue(visit(ctx.value));
        symbolTable.enterSymbol(node);
        return node;
    }

    @Override
    public RootNode visitSetup(Ardu3kParser.SetupContext ctx) {
        SetupNode node = new SetupNode(ctx);
        node.setBlock(visit(ctx.block()));
        return node;
    }

    @Override
    public RootNode visitLoop(Ardu3kParser.LoopContext ctx) {
        LoopNode node = new LoopNode(ctx);
        collectChildren(node, ctx.block().stmt());
        return node;
    }

    // todo fix her - kristian
    public RootNode visitFunctions(List<Ardu3kParser.FunctionContext> ctx) {
        FunctionsNode node = new FunctionsNode();
        collectChildren(node, ctx);

        /*
        collectedTable = cloner.deepClone(symbolTable);
        for(RootNode n : node.children)
            collectedTable.enterSymbol((FunctionNode)n);

        for(Symbol symbol : collectedTable.getTable().values())
            if(symbol instanceof FunctionSymbol)
                internalST.enterSymbol((FunctionNode)symbol.getType(), cloner.deepClone(collectedTable));

        for(RootNode n : node.children) {
            symbolTable.enterSymbol((FunctionNode)n, cloner.deepClone(internalST));
            ((FunctionSymbol)internalST.retrieveSymbol(((FunctionNode) n).getId())).impls =
                    ((FunctionSymbol)symbolTable.retrieveSymbol(((FunctionNode) n).getId())).impls;
        }*/
        return node;
    }
    @Override
    public RootNode visitFunction(Ardu3kParser.FunctionContext ctx) {
        FunctionNode node = new FunctionNode(ctx);
        node.setId(visit(ctx.identifier()));
        node.setParameter(ctx.para != null ? visit(ctx.para) : new ParameterNode());
        node.setBlock(visit(ctx.block()));
        symbolTable.enterSymbol(node);
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

    @Override
    public RootNode visitComment(Ardu3kParser.CommentContext ctx) {
        CommentNode node = new CommentNode(ctx.getText());
        return node;
    }

    @Override
    public RootNode visitUnary_expr(Ardu3kParser.Unary_exprContext ctx) {
        UnaryNegateNode node = new UnaryNegateNode();
        if (ctx.right != null) { node.setExpr(visit(ctx.right)); }
        return node;
    }

    @Override public RootNode visitWhile_stmt(Ardu3kParser.While_stmtContext ctx) {
        WhileNode node = new WhileNode(ctx);
        node.setExpression(visitExpression(ctx.expr));
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
        node.setExpression(visitExpression(ctx.value));
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
        else
            node.setArguments(new ArgumentNode());
        return node;
    }

    @Override
    public RootNode visitPinToggle(Ardu3kParser.PinToggleContext ctx) {
        PinToggleNode node = new PinToggleNode(ctx);
        node.setPinIndexNode(visit(ctx.pin));
        return node;
    }

    @Override
    public RootNode visitPinRead(Ardu3kParser.PinReadContext ctx) {
        PinReadNode node = new PinReadNode(ctx);
        node.setPinIndexNode(visit(ctx.pin));
        return node;
    }

    @Override
    public RootNode visitPinread_assignment(Ardu3kParser.Pinread_assignmentContext ctx) {
        PinReadNode node = new PinReadNode(ctx);
        node.setPinIndexNode(visit(ctx.pin));
        return node;
    }

    @Override
    public RootNode visitPinWriteBool(Ardu3kParser.PinWriteBoolContext ctx) {
        PinWriteNode node = new PinWriteNode(ctx);
        node.setPinIndexNode(visit(ctx.pin));
        node.setWriteValue(visit(ctx.value));
        return node;
    }

    @Override
    public RootNode visitPinWriteInt(Ardu3kParser.PinWriteIntContext ctx) {
        PinWriteNode node = new PinWriteNode(ctx);
        node.setPinIndexNode(visit(ctx.pin));
        node.setWriteValue(new IntegerNode(Integer.valueOf(ctx.value.getText())));
        return node;
    }

    @Override
    public RootNode visitPinIndexId(Ardu3kParser.PinIndexIdContext ctx) {
        PinIndexIdentifierNode node = new PinIndexIdentifierNode(ctx);
        if (ctx.analog !=  null) { node.setbAnalog(true); }
        node.setID(visit(ctx.indexID));
        return node;
    }

    @Override
    public RootNode visitPinIndexInt(Ardu3kParser.PinIndexIntContext ctx) {
        PinIndexNode node = new PinIndexNode(ctx);
        node.setIndex(Integer.valueOf(ctx.index.getText()));
        if (ctx.analog !=  null) { node.setbAnalog(true); }
        return node;
    }

    @Override
    public RootNode visitBeforeStmt(Ardu3kParser.BeforeStmtContext ctx) {
        BeforeNode node = new BeforeNode(ctx);
        node.setTime(visit(ctx.time));
        node.setClockName(visit(ctx.clockName));
        node.setStmt(visit(ctx.exec));
        return node;
    }

    @Override
    public RootNode visitAfterStmt(Ardu3kParser.AfterStmtContext ctx) {
        AfterNode node = new AfterNode(ctx);
        node.setTime(visit(ctx.time));
        node.setClockName(visit(ctx.clockName));
        node.setStmt(visit(ctx.exec));
        return node;
    }

    @Override
    public RootNode visitPrimaryTime(Ardu3kParser.PrimaryTimeContext ctx) {
        TimeNode node = new TimeNode();
        node.setAssignedValue(Integer.valueOf(ctx.val.getText()));

        switch (ctx.type.getText()){
            case "ms":
                node.setType(TimeType.MS);
                break;
            case "sec":
                node.setType(TimeType.S);
                break;
            case "min":
                node.setType(TimeType.M);
                break;
        }

        return node;
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
                case Ardu3kParser.RESET:
                    return new ResetNode(ctx);
                default:
                    return null;
        }
    }

    @Override
    public RootNode visitResetSpecific(Ardu3kParser.ResetSpecificContext ctx) {
        ResetSpecificNode node = new ResetSpecificNode(ctx);
        node.setID(visit(ctx.id));
        return node;
    }

    @Override
    public RootNode visitPinMode(Ardu3kParser.PinModeContext ctx) {
        PinModeNode node = new PinModeNode();
        node.setbOutput(boolVisitPin_mode(ctx.pinMode));
        node.setPinIndexNode(visit(ctx.pin));
        return node;
    }

    public boolean boolVisitPin_mode(Ardu3kParser.Pin_modeContext ctx) {
        switch (ctx.pinMode.getType()){
            case Ardu3kParser.OUTPUT:
            case Ardu3kParser.TRUE:
                return true;
            case Ardu3kParser.INPUT:
            case Ardu3kParser.FALSE:
                return false;
        }
        return false;
    }

    @Override
    public RootNode visitExpression_stmt(Ardu3kParser.Expression_stmtContext ctx) {
        if(ctx.expression() != null)
            return visit(ctx.expression());
        return null;
    }


    @Override
    public RootNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        AssignmentNode node = new AssignmentNode(ctx);
        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));
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
    public RootNode visitDelay(Ardu3kParser.DelayContext ctx) {
        DelayNode node = new DelayNode(ctx);
        node.setTime(visit(ctx.time));
        return node;
    }

    @Override
    public RootNode visitNumber(Ardu3kParser.NumberContext ctx) {
        AbstractNumberNode node;
        switch (ctx.value.getType()) {
            case Ardu3kParser.INTEGER:
                node = new IntegerNode(ctx);
                break;
            case Ardu3kParser.FLOAT:
                node = new FloatNode(ctx);
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
