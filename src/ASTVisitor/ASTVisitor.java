package ASTVisitor;


import ASTVisitor.expression.AbstractInfixExpressionNode;
import ASTVisitor.expression.AssignmentNode;
import ASTVisitor.expression.additive.AbstractInfixAdditiveNode;
import ASTVisitor.expression.additive.MinusNode;
import ASTVisitor.expression.additive.PlusNode;
import ASTVisitor.expression.condition.*;
import ASTVisitor.expression.multiplicative.AbstractInfixMultiplicativeNode;
import ASTVisitor.expression.multiplicative.DivideNode;
import ASTVisitor.expression.multiplicative.ModulusNode;
import ASTVisitor.expression.multiplicative.TimesNode;
import ASTVisitor.expression.relation.*;
import ASTVisitor.statement.*;
import ASTVisitor.structure.*;
import ASTVisitor.primary.*;
import ASTVisitor.structure.RootNode;
import gen.Ardu3kBaseVisitor;
import gen.Ardu3kParser;

public class ASTVisitor extends Ardu3kBaseVisitor<RootNode>
{
    @Override
    public RootNode visitProgram(Ardu3kParser.ProgramContext ctx) {
        ProgramNode node = new ProgramNode();
        node.collectChildren(ctx.define(), node.defineNodes);
        node.setupNode = visitSetup(ctx.setup());
        node.loopNode = visitLoop(ctx.loop());
        node.collectChildren(ctx.functions(), node.functionNodes);
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
        node.collectChildren(ctx.block().block_stmt());
        return node;
    }

    @Override
    public RootNode visitLoop(Ardu3kParser.LoopContext ctx) {
        LoopNode node = new LoopNode();
        node.collectChildren(ctx.block().block_stmt());
        return node;
    }

    @Override
    public RootNode visitFunctions(Ardu3kParser.FunctionsContext ctx) {
        FunctionNode node = new FunctionNode();
        node.id = visit(ctx.identifier());
        node.parameter = visit(ctx.parameter());
        node.block = visit(ctx.block());
        return node;
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
        node.collectChildren(ctx.block_stmt());
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
        ForNode node = new ForNode();
        node.expressionNode = visitExpression(ctx.expr);
        node.value = visitNumber(ctx.value);
        node.collectChildren(ctx.body.block_stmt());
        return node;
    }

    @Override
    public RootNode visitSelection_stmt(Ardu3kParser.Selection_stmtContext ctx) {
        return super.visitSelection_stmt(ctx);
    }

    @Override
    public RootNode visitSwitch_stmt(Ardu3kParser.Switch_stmtContext ctx) {
        SwitchNode node = new SwitchNode();
        node.expression = visitExpression(ctx.expr);
        node.collectChildren(ctx.case_stmt());
        node.defaultnode = visitCase_default(ctx.defaultcase);
        return node;
    }

    @Override
    public RootNode visitCase_stmt(Ardu3kParser.Case_stmtContext ctx) {
        CaseNode node = new CaseNode();
        node.expression = visitExpression(ctx.value);
        node.collectChildren(ctx.block_stmt());
        return node;
    }

    @Override
    public RootNode visitCase_default(Ardu3kParser.Case_defaultContext ctx) {
        DefaultNode node = new DefaultNode();
        node.collectChildren(ctx.block_stmt());
        return node;
    }

    @Override
    public RootNode visitIfdo_stmt(Ardu3kParser.Ifdo_stmtContext ctx) {
        IfElseNode node = new IfElseNode();
        node.condition = visit(ctx.condition);
        node.upperbody = visit(ctx.upperbody);
        if(ctx.lowerbody != null)
            node.lowerbody = visit(ctx.lowerbody);
        return node;
    }

    @Override
    public RootNode visitFunction_stmt(Ardu3kParser.Function_stmtContext ctx) {
        FunctionStmtNode node = new FunctionStmtNode();
        node.id = visit(ctx.id);
        node.arguments = visit(ctx.args);
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
    public RootNode visitExpression(Ardu3kParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public RootNode visitAssignment_expr(Ardu3kParser.Assignment_exprContext ctx) {
        return super.visitAssignment_expr(ctx);
    }

    @Override
    public RootNode visitAssignment(Ardu3kParser.AssignmentContext ctx) {
        AssignmentNode node = new AssignmentNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitConditional_expr(Ardu3kParser.Conditional_exprContext ctx) {
        return super.visitConditional_expr(ctx);
    }

    @Override
    public RootNode visitInfixCondtionalOrExpr(Ardu3kParser.InfixCondtionalOrExprContext ctx) {
        OrNode node = new OrNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
        return node;
    }

    @Override
    public RootNode visitInfixConditionalAndExpr(Ardu3kParser.InfixConditionalAndExprContext ctx) {
        AndNode node = new AndNode();
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
        XorNode node = new XorNode();
        node.left = visit(ctx.left);
        node.right = visit(ctx.right);
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

                default:
                    throw new IllegalArgumentException();
        }

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

    @Override
    public RootNode visitString(Ardu3kParser.StringContext ctx) {
        StringNode node = new StringNode(ctx.string_val());
        node.collectChildren(ctx.string_val());
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
        AbstractBoolNode node;
        switch (ctx.value.getType()) {
            case Ardu3kParser.TRUE:
                node = new TrueNode(Boolean.valueOf(ctx.getText()));
                break;
            case Ardu3kParser.FALSE:
                node = new FalseNode(Boolean.valueOf(ctx.getText()));
                break;

                default:
                    throw new IllegalArgumentException();
        }
        return node;
    }


}
