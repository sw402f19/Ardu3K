package node.statement.time;

import node.RootNode;

public class ResetNode extends RootNode {
    private String timerName;

    public String getTimerName() { return timerName; }
    public void setTimerName(String timerName) { this.timerName = timerName; }

    @Override
    public String toString() { return "RESET"; }
}
