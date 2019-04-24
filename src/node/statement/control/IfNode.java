package node.statement.control;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class IfNode extends AbstractControlNode {

    public IfNode(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "if do ";
    }

    public RootNode getUpperbody() {
        return children.get(1);
    }
    public void setUpperbody(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }
}
