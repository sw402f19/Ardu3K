package node.statement.time;

import gen.Ardu3kParser;
import node.RootNode;

public class ResetNode extends RootNode {
    private RootNode timerName;

    public ResetNode(Ardu3kParser.NotailStatementContext ctx){ super(ctx); }
    public ResetNode(Ardu3kParser.ResetSpecificContext ctx) { super(ctx); }

    public RootNode getClockName() { return timerName; }
    public void setClockName(RootNode timerName) { this.timerName = timerName; }

    @Override
    public String toString() { return "RESET"; }
}
