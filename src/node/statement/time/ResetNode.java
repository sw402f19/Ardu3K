package node.statement.time;

import node.RootNode;

public class ResetNode extends RootNode {
    private RootNode timerName;

    public RootNode getClockName() { return timerName; }
    public void setClockName(RootNode timerName) { this.timerName = timerName; }

    @Override
    public String toString() { return "RESET"; }
}
