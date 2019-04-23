package visitor;

import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.DivideNode;
import node.expression.multiplicative.ExponentialNode;
import node.expression.multiplicative.ModulusNode;
import node.expression.multiplicative.TimesNode;
import node.expression.relation.GreaterEqualNode;
import node.expression.relation.GreaterNode;
import node.expression.relation.LesserEqualNode;
import node.expression.relation.LesserNode;
import node.expression.unary.UnaryNode;
import node.primary.*;
import node.statement.*;
import node.structure.*;

public class BaseASTVisitor<T> implements ASTVisitor<T> {

    public T visitChildren(RootNode node){

        T dast = null;

        if(node.children.size() > 0) {
            for (RootNode n : node.children)
                if (n != null) {
                    dast = n.accept(this);
                }
        }
        return dast;
    }

    @Override
    public T visitRootNode(RootNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitListNode(ListNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitListElement(ListElement node) { return visitChildren(node); }

    @Override
    public T visitProgramNode(ProgramNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitFunctionNode(FunctionNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitDefineNode(DefineNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitLoopNode(LoopNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitSetupNode(SetupNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractNumberNode(AbstractNumberNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractInfixExpressionNode(AbstractInfixExpressionNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAssignmentNode(AssignmentNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitMinusNode(MinusNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitPlusNode(PlusNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAndNode(AndNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitEqualNode(EqualNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitNotNode(NotNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitOrNode(OrNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitXorNode(XorNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitDivideNode(DivideNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitTimesNode(TimesNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitModulusNode(ModulusNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitExponentialNode(ExponentialNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitGreaterEqualNode(GreaterEqualNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitGreaterNode(GreaterNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitLesserEqualNode(LesserEqualNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitLesserNode(LesserNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitUnaryNode(UnaryNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractPrimaryNode(AbstractPrimaryNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitBoolNode(BoolNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitIdentifierNode(IdentifierNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitIntegerNode(IntegerNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitRealNode(RealNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitStringNode(StringNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitStringValNode(StringValNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractStatementNode(AbstractStatementNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitArgumentNode(ArgumentNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitCaseNode(CaseNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitDefaultNode(DefaultNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitElifNode(ElifNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitForNode(ForNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitFunctionStmtNode(FunctionStmtNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitIfNode(IfNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitSwitchNode(SwitchNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitDeclarationNode(DeclarationNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractExpressionNode(AbstractExpressionNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitDefinesNode(DefinesNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitFunctionsNode(FunctionsNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitParameterNode(ParameterNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitWhileNode(WhileNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitBlockNode(BlockNode node) {
        return visitChildren(node);
    }

    public T visit(RootNode node) {
        return visitChildren(node);
    }
}
