package node.statement.control;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class IfNode extends AbstractControlNode {

    public IfNode(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "if";
    }

    public RootNode getUpperbody() {
        return children.get(1);
    }
    public void setUpperbody(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
