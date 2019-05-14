package node.primary.Time;

import node.primary.AbstractPrimaryNode;

public class TimeNode extends AbstractPrimaryNode {
    private int assignedValue = 0;
    private TimeType type;

    public TimeNode() {}

    public void setType(TimeType type) { this.type = type; }

    public void setAssignedValue(int value) { this.assignedValue = value; }

    public int getRealValue() {
        if (type != null) {
            return assignedValue * type.getTimesToMs();
        } else return 0;
    }
}
