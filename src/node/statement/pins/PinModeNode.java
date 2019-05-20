package node.statement.pins;

public class PinModeNode extends AbstractPinStmtNode {
    private boolean bOutput;

    public void setbOutput(boolean bOutput) { this.bOutput = bOutput; }
    public boolean getbOutput() { return bOutput; }

    @Override
    public String toString() {
        return "PINMODE";
    }
}
