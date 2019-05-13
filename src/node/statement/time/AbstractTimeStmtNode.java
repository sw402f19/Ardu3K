package node.statement.time;

import node.RootNode;

public class AbstractTimeStmtNode extends RootNode {
    private String clockName;

    public String getClockName() { return clockName; }
    public void setClockName(String clockName) { this.clockName = clockName; }

    public void setTime(RootNode node){
        if (children.size() > 0) {
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getTime() { return children.get(0); }

    public void setStmt(RootNode node){
        if (children.size() > 1) {
            children.set(1, node);
        } else children.add(node);
    }
    public RootNode getStmt() { return children.get(1); }
}

