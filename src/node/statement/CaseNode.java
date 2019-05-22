package node.statement;

import gen.Ardu3kParser;
import node.RootNode;

public class CaseNode extends RootNode {
    public RootNode expression;

    public CaseNode(Ardu3kParser.Case_stmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "case";
    }

    public RootNode getExpression() {
        return children.get(0);
    }
    public void setExpression(RootNode expression) {
        if(children.size() > 0)
            children.set(0, expression);
        else
            children.add(expression);
    }
}
