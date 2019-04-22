package visitor;

import node.RootNode;
import node.composite.ListNode;
import node.expression.*;
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
import node.scope.*;

public interface ASTVisitor<T> {
    T visitChildren(RootNode node);

    T visitRootNode(RootNode node);

    /**
     * Composite
     */
    T visitListNode(ListNode node);

    /**
     * Structure
     */

    T visitBlockNode(BlockNode node);

    T visitProgramNode(ProgramNode node);

    T visitFunctionNode(FunctionNode node);

    T visitDefineNode(DefineNode node);

    T visitDefinesNode(DefinesNode node);

    T visitFunctionsNode(FunctionsNode node);

    T visitParameterNode(ParameterNode node);

    T visitLoopNode(LoopNode node);

    T visitSetupNode(SetupNode node);

    /**
     * Primaries
     */

    T visitAbstractNumberNode(AbstractNumberNode node);

    T visitAbstractPrimaryNode(AbstractPrimaryNode node);

    T visitBoolNode(BoolNode node);

    T visitIdentifierNode(IdentifierNode node);

    T visitIntegerNode(IntegerNode node);

    T visitRealNode(RealNode node);

    T visitStringNode(StringNode node);

    T visitStringValNode(StringValNode node);

    T visitUndefindedNode(UndefinedNode node);

    /**
     * Statements
     */

    T visitAbstractStatementNode(AbstractStatementNode node);

    T visitArgumentNode(ArgumentNode node);

    T visitCaseNode(CaseNode node);

    T visitDefaultNode(DefaultNode node);

    T visitElifNode(ElifNode node);

    T visitForNode(ForNode node);

    T visitFunctionStmtNode(FunctionStmtNode node);

    T visitIfNode(IfNode node);

    T visitSwitchNode(SwitchNode node);

    T visitWhileNode(WhileNode node);

    /**
     * Expressions
     */
    T visitAbstractExpressionNode(AbstractExpressionNode node);

    T visitAbstractInfixExpressionNode(AbstractInfixExpressionNode node);

    T visitDeclarationNode(DeclarationNode node);

    T visitAssignmentNode(AssignmentNode node);


    /**
     * Additive
     */
    T visitMinusNode(MinusNode node);

    T visitPlusNode(PlusNode node);

    /**
     * Condition
     */
    T visitAndNode(AndNode node);

    T visitEqualNode(EqualNode node);

    T visitNotNode(NotNode node);

    T visitOrNode(OrNode node);

    T visitXorNode(XorNode node);

    /**
     * Multiplicative
     */
    T visitDivideNode(DivideNode node);

    T visitTimesNode(TimesNode node);

    T visitModulusNode(ModulusNode node);

    T visitExponentialNode(ExponentialNode node);

    /**
     * Relational
     */

    T visitGreaterEqualNode(GreaterEqualNode node);

    T visitGreaterNode(GreaterNode node);

    T visitLesserEqualNode(LesserEqualNode node);

    T visitLesserNode(LesserNode node);

    /**
     * Unary
     */

    T visitUnaryNode(UnaryNode node);

}
