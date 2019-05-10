package symbol;

import com.rits.cloning.Cloner;
import node.RootNode;
import node.scope.FunctionNode;
import node.statement.FunctionStmtNode;

import java.util.ArrayList;

public class FunctionSymbol extends Symbol {

    private ArrayList<FunctionNode> impls = new ArrayList<>();
    private SymbolTable symTable;


    public FunctionSymbol(RootNode name, RootNode type, SymbolTable symTable, int depth) {
        super(name, type, depth);
        this.symTable = symTable;
    }

    public FunctionNode getImpl(FunctionStmtNode node) {
        FunctionNode impl = null;

        for(FunctionNode n : impls) {
            for(int i = 0; i < node.getArguments().children.size(); i++) {
                if (!node.getArguments().children.get(i).getClass()
                        .isInstance(n.getParameter().children.get(i)))
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
