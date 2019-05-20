package node.statement.time;

import gen.Ardu3kParser;

public class AfterNode extends AbstractTimeStmtNode {
    public AfterNode(Ardu3kParser.AfterStmtContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "AFTER";
    }
}
