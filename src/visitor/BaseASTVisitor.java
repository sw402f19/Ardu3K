package visitor;

import node.RootNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
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
    public T visitAbstractInfixExpressionNode(AbstractInfixExpressionNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAssignmentNode(AssignmentNode node) {
        return visitChildren(node);
    }

    @Override
    public T visitAbstractBoolNode(BoolNode node) {
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
    public T visitIdentifier(IdentifierNode node) {
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

    public T visit(RootNode node) {
        return node.accept(this);
    }
}
