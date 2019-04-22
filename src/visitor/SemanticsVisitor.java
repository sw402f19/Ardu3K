package visitor;

import node.RootNode;
import node.expression.*;
import node.expression.type.IllegalTypeException;
import node.primary.IdentifierNode;
import node.primary.UndefinedNode;
import node.statement.*;
import node.structure.*;
import symbol.SymbolTable;

public class SemanticsVisitor extends BaseASTVisitor<RootNode> {

    private SymbolTable symbolTable = SymbolTable.getInstance();

    @Override
    public RootNode visitChildren(RootNode node){
        RootNode dast = null;

        if(node.children.size() > 0) {
            for (RootNode n : node.children)
                if (n != null) {
                    if(n instanceof AbstractInfixExpressionNode)
                        try {
                            dast = ((AbstractInfixExpressionNode) n.accept(new TypeVisitor()));
                        } catch (IllegalTypeException e) {
                            System.out.println(e.getMessage());
                        }
                    dast = n.accept(this);
                }
        }
        return dast;
    }

    @Override
    public RootNode visitAssignmentNode(AssignmentNode node) {
        if(!symbolTable.isPresent(node.getLeft()))
            return visitDeclarationNode(new DeclarationNode(node));
        else {
            //todo check if type compatible
        }

        return node;
    }

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        symbolTable.enterSymbol(node);

        return node;
    }

    // todo temporary error handling
    @Override
    public RootNode visitIdentifierNode(IdentifierNode node) {
        if(symbolTable.retrieveSymbol(node) == null)
            System.out.println(node.getLine()+" Identifier \""+node.toString()+"\" not declared");
        else
            return node;
        return null;
    }

    @Override
    public RootNode visitAbstractExpressionNode(AbstractExpressionNode node) {
        return super.visitAbstractExpressionNode(node);
    }

    @Override
    public RootNode visitProgramNode(ProgramNode node) {
        super.visit(node.getDefinesNode());
        super.visit(node.getFunctionsNode());
        super.visit(node.getSetupNode());
        super.visit(node.getLoopNode());
        return node;
    }
    public RootNode visitSetupNode(SetupNode node) {
        symbolTable.openScope();
        visitBlockNode(node.getBlock());
        return node;
    }

    @Override
    public RootNode visitDefinesNode(DefinesNode node) {
        symbolTable.openScope();
        super.visit(node);
        return node;
    }

    @Override
    public RootNode visitDefineNode(DefineNode node) {
        // todo typecheck children
        return node;
    }

    @Override
    public RootNode visitLoopNode(LoopNode node) {
        symbolTable.openScope();
        super.visit(node);
        //symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitForNode(ForNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitSwitchNode(SwitchNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitIfNode(IfNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitBlockNode(BlockNode node) {
        symbolTable.openScope();
        super.visitBlockNode(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitElifNode(ElifNode node) {
        symbolTable.openScope();
        super.visit(node);
        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitFunctionNode(FunctionNode node) {
        symbolTable.enterSymbol(node);
        symbolTable.openScope();


        node.getParameter().accept(this);
        node.getBlock().accept(this);




       // System.out.println(node.getParameter().children.size());
        //System.out.println(node.getParameter().children);

        symbolTable.closeScope();
        return node;
    }

    @Override
    public RootNode visitFunctionsNode(FunctionsNode node) {
        symbolTable.openScope();

        return super.visitFunctionsNode(node);
    }

    @Override
    public RootNode visitParameterNode(ParameterNode node) {
        symbolTable.openScope();
        node.children.forEach(e -> symbolTable.enterSymbol((IdentifierNode) e));
        return super.visitParameterNode(node);
    }

    /*



    @Override
    public RootNode visitFunctionStmtNode(FunctionStmtNode node) {
        return super.visitFunctionStmtNode(node);
    }*/


}
