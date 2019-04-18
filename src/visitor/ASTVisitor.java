package visitor;

import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.primary.*;
import node.statement.*;
import node.structure.*;

public interface ASTVisitor<T> {
    T visitChildren(RootNode node);

    T visitRootNode(RootNode node);

    T visitProgramNode(ProgramNode node);

    T visitFunctionNode(FunctionNode node);

    T visitDefineNode(DefineNode node);

    T visitLoopNode(LoopNode node);

    T visitSetupNode(SetupNode node);

    T visitAbstractInfixExpressionNode(AbstractInfixExpressionNode node);

    T visitAssignmentNode(AssignmentNode node);

    T visitAbstractBoolNode(BoolNode node);

    T visitIdentifierNode(IdentifierNode node);

    T visitIntegerNode(IntegerNode node);

    T visitRealNode(RealNode node);

    T visitStringNode(StringNode node);

    T visitElifNode(ElifNode node);

    T visitForNode(ForNode node);

    T visitFunctionStmtNode(FunctionStmtNode node);

    T visitIfNode(IfNode node);

    T visitSwitchNode(SwitchNode node);

    T visitDeclarationNode(DeclarationNode node);
}
