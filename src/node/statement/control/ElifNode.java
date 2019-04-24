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
        return "if do else ";
    }

    public RootNode getLowerbody() {
        return children.get(0);
    }
    public void setLowerbody(RootNode lowerbody) {
        children.set(0, lowerbody);
    }

}
