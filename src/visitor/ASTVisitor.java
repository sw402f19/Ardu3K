package visitor;

import node.structure.RootNode;

public interface ASTVisitor<T> {
    T visitChildren(RootNode node);

   /*void visitRootNode(RootNode node);

    void visitProgramNode(ProgramNode node);

    void visitFunctionNode(FunctionNode node);

    void visitDefineNode(DefineNode node);

    void visitLoopNode(LoopNode node);

    void visitSetupNode(SetupNode node);

    void visitAbstractInfixExpressionNode(AbstractInfixExpressionNode node);

    void visitAssignmentNode(AssignmentNode node);

    void visitAbstractBoolNode(AbstractBoolNode node);

    void visitIdentifierNode(IdentifierNode node);

    void visitIntegerNode(IntegerNode node);

    void visitRealNode(RealNode node);

    void visitStringNode(StringNode node);

    void visitElifNode(ElifNode node);

    void visitForNode(ForNode node);

    void visitFunctionStmtNode(FunctionStmtNode node);

    void visitIfNode(IfNode node);

    void visitSwitchNode(SwitchNode node);*/

}
