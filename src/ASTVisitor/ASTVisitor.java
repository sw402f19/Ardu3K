package ASTVisitor;


import ASTVisitor.expression.AbstractInfixExpressionNode;
import ASTVisitor.expression.condition.AndNode;
import ASTVisitor.expression.condition.EqualNode;
import ASTVisitor.expression.condition.NotNode;
import ASTVisitor.expression.condition.OrNode;
import ASTVisitor.structure.*;
import ASTVisitor.primary.*;
import ASTVisitor.structure.RootNode;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.RuleContext;

import java.util.List;


public class ASTVisitor extends Ardu3kBaseVisitor<RootNode>
{
    @Override
    public RootNode visitProgram(Ardu3kParser.ProgramContext ctx) {
        ProgramNode node = new ProgramNode();
        ctx.define().forEach(e -> node.defineNodes.add(visitDefine(e)));
        node.setupNode = visitSetup(ctx.setup());
        node.loopNode = visitLoop(ctx.loop());
        ctx.functions().forEach(e -> node.functionNodes.add(visitFunctions(e)));
        return node;
    }

    @Override
    public RootNode visitDefine(Ardu3kParser.DefineContext ctx) {
        DefineNode node = new DefineNode();
        node.id = visit(ctx.id);
        node.value = visit(ctx.value);
        return node;
    }

    @Override
    public RootNode visitSetup(Ardu3kParser.SetupContext ctx) {
        SetupNode node = new SetupNode();
        ctx.block().block_stmt().forEach(e -> node.blockStatements.add(visitBlock_stmt(e)));
        return node;
    }

    @Override
    public RootNode visitLoop(Ardu3kParser.LoopContext ctx) {
        LoopNode node = new LoopNode();
        ctx.block().block_stmt().forEach(e -> node.blockStatements.add(visitBlock_stmt(e)));
        return node;
    }

    @Override
    public RootNode visitFunctions(Ardu3kParser.FunctionsContext ctx) {
        FunctionNode node = new FunctionNode();
        node.id = visit(ctx.identifier());
        node.parameter = visit(ctx.parameter());
        ctx.block().block_stmt().forEach(e -> node.blockStatements.add(visitBlock_stmt(e)));
        return node;
    }

    @Override
    public RootNode visitParameter(Ardu3kParser.ParameterContext ctx) {
        ParameterNode node = new ParameterNode();
        return visitParameter(ctx, node);
    }
    private RootNode visitParameter(Ardu3kParser.ParameterContext ctx, ParameterNode node) {
        node.children.add(new IdentifierNode(ctx.id));
        if(ctx.para != null)
            visitParameter(ctx.para, node);
        return node;
    }

    @Override
    public RootNode visitBlock(Ardu3kParser.BlockContext ctx) {
        BlockNode node = new BlockNode();
        ctx.block_stmt().forEach(e -> node.blockStmt.add(visitBlock_stmt(e)));
        return node;
    }

    @Override
    public RootNode visitBlock_stmt(Ardu3kParser.Block_stmtContext ctx) {
        return super.visitBlock_stmt(ctx);
    }

    @Override
    public RootNode visitStmt(Ardu3kParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public RootNode visitIterative_stmt(Ardu3kParser.Iterative_stmtContext ctx) {
        return super.visitIterative_stmt(ctx);
    }

    @Override
    public RootNode visitFor_stmt(Ardu3kParser.For_stmtContext ctx) {
        return super.visitFor_stmt(ctx);
    }

    @Override
    public RootNode visitSelection_stmt(Ardu3kParser.Selection_stmtContext ctx) {
        return super.visitSelection_stmt(ctx);
    }

    @Override
    public RootNode visitSwitch_stmt(Ardu3kParser.Switch_stmtContext ctx) {
        return super.visitSwitch_stmt(ctx);
    }

    @Override
    public RootNode visitCase_stmt(Ardu3kParser.Case_stmtContext ctx) {
        return super.visitCase_stmt(ctx);
    }

    @Override
    public RootNode visitCase_default(Ardu3kParser.Case_defaultContext ctx) {
        return super.visitCase_default(ctx);
    }

    @Override
    public RootNode visitIfdo_stmt(Ardu3kParser.Ifdo_stmtContext ctx) {
        return super.visitIfdo_stmt(ctx);
    }

    @Override
    public RootNode visitFunction_stmt(Ardu3kParser.Function_stmtContext ctx) {
        return super.visitFunction_stmt(ctx);
    }

    @Override
    public RootNode visitArgs(Ardu3kParser.ArgsContext ctx) {
        return super.visitArgs(ctx);
    }

    @Override
    public RootNode visitArgs_list(Ardu3kParser.Args_listContext ctx) {
        return super.visitArgs_list(ctx);
    }

    @Override
    public RootNode visitExpression_stmt(Ardu3kParser.Expression_stmtContext ctx) {
        return super.visitExpression_stmt(ctx);
    }

    @Override
    public RootNode visitExpression(Ardu3kParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public RootNode visitAssignment_expr(Ardu3kParser.Assignment_exprContext ctx) {
        return super.visitAssignment_expr(ctx);
    }

    @Override
    public RootNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public RootNode visitConditional_expr(Ardu3kParser.Conditional_exprContext ctx) {
        return super.visitConditional_expr(ctx);
    }

    @Override
    public RootNode visitInfixCondtionalOrExpr(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        AbstractInfixExpressionNode node = new OrNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitInfixConditionalAndExpr(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        AbstractInfixExpressionNode node = new AndNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitConditionalEqualExpr(Ardu3kParser.ConditionalEqualExprContext ctx) {
        return super.visitConditionalEqualExpr(ctx);
    }

    @Override
    public RootNode visitInfixConditionalXorExpr(Ardu3kParser.InfixConditionalXorExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitInfixEqualExpr(Ardu3kParser.InfixEqualExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        switch (ctx.op.getType()) {
            case Ardu3kParser.EQUALS:
                node = new EqualNode();
            case Ardu3kParser.NOT:
                node = new NotNode();
        }
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);

        return node;
    }

    @Override
    public RootNode visitRelationalExpr(Ardu3kParser.RelationalExprContext ctx) {
        return super.visitRelationalExpr(ctx);
    }

    @Override
    public RootNode visitInfixRelationalExpr(Ardu3kParser.InfixRelationalExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitAdditiveExpr(Ardu3kParser.AdditiveExprContext ctx) {
        return super.visitAdditiveExpr(ctx);
    }

    @Override
    public RootNode visitInfixAdditiveExpr(Ardu3kParser.InfixAdditiveExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitMultiplicativeExpr(Ardu3kParser.MultiplicativeExprContext ctx) {
        return super.visitMultiplicativeExpr(ctx);
    }

    @Override
    public RootNode visitInfixMultiplicativeExpr(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitPrimaryExpr(Ardu3kParser.PrimaryExprContext ctx) {
        return super.visitPrimaryExpr(ctx);
    }

    @Override
    public RootNode visitPrimary(Ardu3kParser.PrimaryContext ctx) {
        return super.visitPrimary(ctx);
    }

    @Override
    public RootNode visitPrimary_expr(Ardu3kParser.Primary_exprContext ctx) {
        return super.visitPrimary_expr(ctx);
    }

    @Override
    public RootNode visitIdentifier(Ardu3kParser.IdentifierContext ctx) {
        return new IdentifierNode(ctx);
    }

    // TODO Kristian here
    @Override
    public RootNode visitString(Ardu3kParser.StringContext ctx) {
        List<Ardu3kParser.String_valContext> list = ctx.string_val();
        StringNode node = new StringNode();
        //list.forEach(e -> node.charList.add(super.visitParameter(e)));
        return node;
    }

    @Override
    public RootNode visitString_val(Ardu3kParser.String_valContext ctx) {
        return super.visitString_val(ctx);
    }

    @Override
    public RootNode visitNumber(Ardu3kParser.NumberContext ctx) {
        AbstractNumberNode node = new AbstractNumberNode() {};
        switch (ctx.value.getType()) {
            case Ardu3kParser.INTEGER:
                node = new IntegerNode(ctx);
            case Ardu3kParser.REAL:
                node = new RealNode(ctx);
        }
        return node;
    }

    @Override
    public RootNode visitBool(Ardu3kParser.BoolContext ctx) {
        AbstractBoolNode node = new AbstractBoolNode() {};
        switch (ctx.value.getType()) {
            case Ardu3kParser.TRUE:
                node = new TrueNode(Boolean.valueOf(ctx.getText()));
            case Ardu3kParser.FALSE:
                node = new FalseNode(Boolean.valueOf(ctx.getText()));
        }
        return node;
    }

}
