package node.statement.time;

import gen.Ardu3kParser;

public class BeforeNode extends AbstractTimeStmtNode {
    public BeforeNode(Ardu3kParser.BeforeStmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "BEFORE";
    }
}
