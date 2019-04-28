package node.statement.control;

import gen.Ardu3kParser;
import node.RootNode;

public class ForNode extends AbstractControlNode {
    public ForNode(Ardu3kParser.For_stmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "for-loop";
    }

    public RootNode getValue() {
        return children.get(1);
    }
    public void setValue(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
    public RootNode getStmt() {
        return children.get(2);
    }
    public void setStmt(RootNode node) {
        if(children.size() > 2)
            children.set(2, node);
        else
            children.add(node);
    }
}
