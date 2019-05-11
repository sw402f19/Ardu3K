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

    public ArrayList<FunctionNode> impls = new ArrayList<>();
    public SymbolTable symTable;


    public FunctionSymbol(RootNode name, RootNode type, SymbolTable symTable, int depth) {
        super(name, type, depth);
        this.symTable = symTable;
    }

    public FunctionNode getImpl(FunctionStmtNode call) throws SemanticException {
        FunctionNode impl = null;
        PrimaryVisitor internalVisitor = new PrimaryVisitor(symTable);
        PrimaryVisitor externalVisitor = new PrimaryVisitor(call.st);
        ParameterNode parameter;

        for(FunctionNode n : impls) {
            parameter = (ParameterNode)n.getParameter();
            for(int i = 0; i < call.getArguments().children.size(); i++) {
                if (!externalVisitor.visit(call.getArguments().children.get(i)).getClass()
                        .isInstance(internalVisitor.visit(parameter.types.get(i))))
                    break;
                if(i == call.getArguments().children.size() -1)
                    impl = n;
            }
        }
        return impl;
    }
    public boolean containsImpl(FunctionStmtNode call) throws SemanticException {
        boolean contains = false;
        PrimaryVisitor visitor = new PrimaryVisitor(symTable);
        FunctionNode template = (FunctionNode)getType();
        ParameterNode parameter;

        if(call.getArguments().children.size() != template.getParameter().children.size())
            throw ExceptionFactory.produce("undeclaredidentifier", call.getId());

        for(FunctionNode n : impls) {
            parameter = (ParameterNode)n.getParameter();
            for(int i = 0; i < call.getArguments().children.size(); i++) {
                if (!visitor.visit(call.getArguments().children.get(i)).getClass()
                        .isInstance(visitor.visit(parameter.types.get(i))))
                    break;
                if(i == call.getArguments().children.size() -1)
                    contains = true;
            }
        }
        return contains;
    }

    public void addImpl(FunctionStmtNode call) throws SemanticException {
        Cloner cloner = new Cloner();
        FunctionNode nodeToAdd = new FunctionNode();
        FunctionNode template = (FunctionNode) getType();
        PrimaryVisitor externalVisitor = new PrimaryVisitor(call.st);
        ParameterNode parameter = cloner.deepClone((ParameterNode) template.getParameter());

        nodeToAdd.setId(cloner.deepClone(template.getId()));
        for(int i = 0; i < template.getParameter().children.size(); i++) {
            parameter.types.add(externalVisitor.visit(call.getArguments().children.get(i)));
        }
        nodeToAdd.setParameter(parameter);
        nodeToAdd.setBlock(cloner.deepClone(template.getBlock()));
        impls.add(nodeToAdd);
    }
}
