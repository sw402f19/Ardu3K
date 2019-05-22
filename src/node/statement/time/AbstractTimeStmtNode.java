package node.statement.time;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AbstractTimeStmtNode extends RootNode {
    public AbstractTimeStmtNode(RootNode parent) {
        super(parent);
    }

    public AbstractTimeStmtNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public AbstractTimeStmtNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public AbstractTimeStmtNode() {
    }

    private RootNode clockName;

    public RootNode getClockName() {
        return clockName;
    }
    public void setClockName(RootNode clockName) { this.clockName = clockName; }

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

