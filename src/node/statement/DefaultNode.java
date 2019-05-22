package node.statement;

import gen.Ardu3kParser;
import node.RootNode;

public class DefaultNode extends RootNode {

    public DefaultNode(Ardu3kParser.Case_defaultContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "default ";
    }
}
