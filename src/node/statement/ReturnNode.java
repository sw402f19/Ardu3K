package node.statement;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReturnNode extends RootNode {

    public ReturnNode(RootNode node, ParserRuleContext ctx) {
        super(ctx);
        setExpression(node);
    }


    public RootNode getExpression() {
        if(children.size() > 0)
            return children.get(0);
        else
            return null;
    }
    public void setExpression(RootNode expression) {
        if(children.size() > 0)
            children.set(0, expression);
        else
            children.add(expression);
    }
}
