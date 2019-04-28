package node.statement.control;

import gen.Ardu3kParser;
import node.RootNode;
import node.statement.control.IfNode;

public class ElifNode extends IfNode {

    public ElifNode(Ardu3kParser.ElseTrailingIfContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "if-else";
    }

    public RootNode getLowerbody() {
        return children.get(2);
    }
    public void setLowerbody(RootNode lowerbody) {
        if(children.size() > 2)
            children.set(2, lowerbody);
        else
            children.add(lowerbody);
    }

}
