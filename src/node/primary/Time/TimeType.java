package node.primary.Time;

public enum TimeType {
    MS(1), S(1000), M(60000);

    TimeType(int times){ this.timesToMs = times; }

    private int timesToMs;

    public int getTimesToMs(){ return this.timesToMs; }
}
