package node.statement;

import gen.Ardu3kParser;
import node.RootNode;
import symbol.SymbolTable;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class FunctionStmtNode extends RootNode {

    public SymbolTable st;

    public FunctionStmtNode(Ardu3kParser.Function_stmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);;
    }

    public RootNode getArguments() {
        return children.get(1);
    }
    public void setArguments(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }

}
