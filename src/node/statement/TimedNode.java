package node.statement;

import node.RootNode;
import node.expression.multiplicative.TimesNode;

import java.util.Random;

public class TimedNode extends RootNode {
    private int waitTime;
    private String timerName;

    public TimedNode() {
        // Create a random timer-name for this nodes timer
        Random rnd = new Random();
        timerName = "timer" + Integer.toUnsignedLong(rnd.nextInt());
    }

    public int getWaitTime() { return waitTime; }
    public void setWaitTime(int waitTime) { this.waitTime = waitTime; }


    public void setFuncID(RootNode node) {
        if (children.size() > 0){
            children.set(0, node);
        } else children.add(node);
    }
    public RootNode getFuncID() { return children.get(0); }

    public String getTimerName() { return timerName; }
}
