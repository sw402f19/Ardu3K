package node.scope;

import gen.Ardu3kParser;

public class LoopNode extends AbstractScopeNode {

    public LoopNode(Ardu3kParser.LoopContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Loop";
    }
}
