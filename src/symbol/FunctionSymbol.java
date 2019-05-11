package symbol;

import com.rits.cloning.Cloner;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.scope.FunctionNode;
import node.scope.ParameterNode;
import node.statement.FunctionStmtNode;
import visitor.semantic.PrimaryVisitor;

import java.util.ArrayList;

public class FunctionSymbol extends Symbol {

    private ArrayList<FunctionNode> impls = new ArrayList<>();
    public SymbolTable symTable;


    public FunctionSymbol(RootNode name, RootNode type, SymbolTable symTable, int depth) {
        super(name, type, depth);
        this.symTable = symTable;
    }

    public FunctionNode getImpl(FunctionStmtNode node, SymbolTable externalST) throws SemanticException {
        FunctionNode impl = null;
        PrimaryVisitor internalVisitor = new PrimaryVisitor(symTable);
        PrimaryVisitor externalVisitor = new PrimaryVisitor(externalST);
        ParameterNode parameter;

        for(FunctionNode n : impls) {
            parameter = (ParameterNode)n.getParameter();
            for(int i = 0; i < node.getArguments().children.size(); i++) {
                if (!externalVisitor.visit(node.getArguments().children.get(i)).getClass()
                        .isInstance(internalVisitor.visit(parameter.types.get(i))))
                    break;
                if(i == node.getArguments().children.size() -1)
                    impl = n;
            }
        }
        return impl;
    }
    public boolean containsImpl(FunctionStmtNode node) {
        boolean contains = false;

        for(FunctionNode n : impls) {
            for(int i = 0; i < node.getArguments().children.size(); i++) {
                if (!node.getArguments().children.get(i).getClass()
                        .isInstance(n.getParameter().children.get(i)))
                    break;
                if(i == node.getArguments().children.size() -1)
                    contains = true;
            }
        }
        return contains;
    }

    public void addImpl(FunctionStmtNode node) {
        Cloner cloner = new Cloner();
        FunctionNode nodeToAdd = new FunctionNode();
        FunctionNode template = (FunctionNode) getType();

        FunctionNode type = (FunctionNode) this.getType();
        if(!containsImpl(node)) {
            nodeToAdd.setBlock(cloner.deepClone(template.getBlock()));
            nodeToAdd.setId(cloner.deepClone(template.getId()));

        }
    }
}
