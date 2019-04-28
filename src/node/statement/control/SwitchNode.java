package node.statement.control;

import gen.Ardu3kParser;
import node.RootNode;

public class SwitchNode extends AbstractControlNode {

    public SwitchNode(Ardu3kParser.Switch_stmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Switch";
    }

    public RootNode getDefaultnode() {
        return children.get(1);
    }
    public void setDefaultNode(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }
}
