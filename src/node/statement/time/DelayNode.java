package node.statement.time;

import org.antlr.v4.runtime.ParserRuleContext;

public class DelayNode extends AbstractTimeStmtNode {
    public DelayNode(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "delay";
    }
}
