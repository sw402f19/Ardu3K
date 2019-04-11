package ASTVisitor.structure;

import java.util.ArrayList;

public class PrettyASTPrinter extends AbstractASTVisitor implements ASTVisitor {
    int spacesToPrint = 0;

    @Override
    public void visitChildren(RootNode node) {
        super.visitChildren(node);
        System.out.println(node.toString());
    }

    public void visitChildren(ArrayList<RootNode> nodes) {
        nodes.forEach(e -> visitChildren(e));
    }
/*
    @Override
    public void visitRootNode(RootNode node) {
        super.visitChildren(node);
        System.out.println(node.toString());
    }

    @Override
    public void visitProgramNode(ProgramNode node) {
        visitChildren(node.defineNodes);
       visitChildren(node.functionNodes);
        visitLoopNode(node.loopNode);
        visitSetupNode(node.setupNode);
        System.out.println(node.toString());
    }


    @Override
    public void visitFunctionNode(FunctionNode node) {

    }

    @Override
    public void visitDefineNode(DefineNode node) {

    }*/


}
