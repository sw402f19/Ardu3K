package node.statement.control;

import gen.Ardu3kParser;
import node.RootNode;

public class WhileNode extends AbstractIterativeNode {
    public WhileNode(Ardu3kParser.While_stmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "While-loop"; }

    public RootNode getStmt() { return children.get(1); }
    public void setStmt(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
