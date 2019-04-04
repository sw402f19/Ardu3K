package ASTVisitor;


import ASTVisitor.expression.AbstractInfixExpressionNode;
import ASTVisitor.expression.condition.AndNode;
import ASTVisitor.expression.condition.EqualNode;
import ASTVisitor.expression.condition.NotNode;
import ASTVisitor.expression.condition.OrNode;
import ASTVisitor.structure.BaseNode;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;

class ASTVisitor extends Ardu3kBaseVisitor<BaseNode>
{
    @Override
    public BaseNode visitProgramUnit(Ardu3kParser.ProgramUnitContext ctx) {
        return super.visitProgramUnit(ctx);
    }

    @Override
    public BaseNode visitDefine(Ardu3kParser.DefineContext ctx) {
        return super.visitDefine(ctx);
    }

    @Override
    public BaseNode visitSetup(Ardu3kParser.SetupContext ctx) {
        return super.visitSetup(ctx);
    }

    @Override
    public BaseNode visitLoop(Ardu3kParser.LoopContext ctx) {
        return super.visitLoop(ctx);
    }

    @Override
    public BaseNode visitFunctions(Ardu3kParser.FunctionsContext ctx) {
        return super.visitFunctions(ctx);
    }

    @Override
    public BaseNode visitParameters(Ardu3kParser.ParametersContext ctx) {
        return super.visitParameters(ctx);
    }

    @Override
    public BaseNode visitParameters_list(Ardu3kParser.Parameters_listContext ctx) {
        return super.visitParameters_list(ctx);
    }

    @Override
    public BaseNode visitBlock(Ardu3kParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public BaseNode visitBlock_stmt(Ardu3kParser.Block_stmtContext ctx) {
        return super.visitBlock_stmt(ctx);
    }

    @Override
    public BaseNode visitStmt(Ardu3kParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public BaseNode visitIterative_stmt(Ardu3kParser.Iterative_stmtContext ctx) {
        return super.visitIterative_stmt(ctx);
    }

    @Override
    public BaseNode visitFor_stmt(Ardu3kParser.For_stmtContext ctx) {
        return super.visitFor_stmt(ctx);
    }

    @Override
    public BaseNode visitSelection_stmt(Ardu3kParser.Selection_stmtContext ctx) {
        return super.visitSelection_stmt(ctx);
    }

    @Override
    public BaseNode visitSwitch_stmt(Ardu3kParser.Switch_stmtContext ctx) {
        return super.visitSwitch_stmt(ctx);
    }

    @Override
    public BaseNode visitCase_stmt(Ardu3kParser.Case_stmtContext ctx) {
        return super.visitCase_stmt(ctx);
    }

    @Override
    public BaseNode visitCase_default(Ardu3kParser.Case_defaultContext ctx) {
        return super.visitCase_default(ctx);
    }

    @Override
    public BaseNode visitIfdo_stmt(Ardu3kParser.Ifdo_stmtContext ctx) {
        return super.visitIfdo_stmt(ctx);
    }

    @Override
    public BaseNode visitFunction_stmt(Ardu3kParser.Function_stmtContext ctx) {
        return super.visitFunction_stmt(ctx);
    }

    @Override
    public BaseNode visitArgs(Ardu3kParser.ArgsContext ctx) {
        return super.visitArgs(ctx);
    }

    @Override
    public BaseNode visitArgs_list(Ardu3kParser.Args_listContext ctx) {
        return super.visitArgs_list(ctx);
    }

    @Override
    public BaseNode visitExpression_stmt(Ardu3kParser.Expression_stmtContext ctx) {
        return super.visitExpression_stmt(ctx);
    }

    @Override
    public BaseNode visitExpression(Ardu3kParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public BaseNode visitAssignment_expr(Ardu3kParser.Assignment_exprContext ctx) {
        return super.visitAssignment_expr(ctx);
    }

    @Override
    public BaseNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public BaseNode visitConditional_expr(Ardu3kParser.Conditional_exprContext ctx) {
        return super.visitConditional_expr(ctx);
    }

    @Override
    public BaseNode visitInfixCondtionalOrExpr(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        AbstractInfixExpressionNode node = new OrNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitInfixConditionalAndExpr(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        AbstractInfixExpressionNode node = new AndNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitConditionalEqualExpr(Ardu3kParser.ConditionalEqualExprContext ctx) {
        return super.visitConditionalEqualExpr(ctx);
    }

    @Override
    public BaseNode visitInfixConditionalXorExpr(Ardu3kParser.InfixConditionalXorExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitInfixEqualExpr(Ardu3kParser.InfixEqualExprContext ctx) {
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
    public BaseNode visitRelationalExpr(Ardu3kParser.RelationalExprContext ctx) {
        return super.visitRelationalExpr(ctx);
    }

    @Override
    public BaseNode visitInfixRelationalExpr(Ardu3kParser.InfixRelationalExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitAdditiveExpr(Ardu3kParser.AdditiveExprContext ctx) {
        return super.visitAdditiveExpr(ctx);
    }

    @Override
    public BaseNode visitInfixAdditiveExpr(Ardu3kParser.InfixAdditiveExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitMultiplicativeExpr(Ardu3kParser.MultiplicativeExprContext ctx) {
        return super.visitMultiplicativeExpr(ctx);
    }

    @Override
    public BaseNode visitInfixMultiplicativeExpr(Ardu3kParser.InfixMultiplicativeExprContext ctx) {
        AbstractInfixExpressionNode node = new AbstractInfixExpressionNode() {};
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public BaseNode visitPrimaryExpr(Ardu3kParser.PrimaryExprContext ctx) {
        return super.visitPrimaryExpr(ctx);
    }

    @Override
    public BaseNode visitPrimary(Ardu3kParser.PrimaryContext ctx) {
        return super.visitPrimary(ctx);
    }

    @Override
    public BaseNode visitPrimary_expr(Ardu3kParser.Primary_exprContext ctx) {
        return super.visitPrimary_expr(ctx);
    }

    @Override
    public BaseNode visitIdentifier(Ardu3kParser.IdentifierContext ctx) {
        return super.visitIdentifier(ctx);
    }

    @Override
    public BaseNode visitString(Ardu3kParser.StringContext ctx) {
        return super.visitString(ctx);
    }

    @Override
    public BaseNode visitString_val(Ardu3kParser.String_valContext ctx) {
        return super.visitString_val(ctx);
    }

    @Override
    public BaseNode visitLiteral(Ardu3kParser.LiteralContext ctx) {
        return super.visitLiteral(ctx);
    }

    @Override
    public BaseNode visitNumber(Ardu3kParser.NumberContext ctx) {
        return super.visitNumber(ctx);
    }

    @Override
    public BaseNode visitBool(Ardu3kParser.BoolContext ctx) {
        return super.visitBool(ctx);
    }
}
