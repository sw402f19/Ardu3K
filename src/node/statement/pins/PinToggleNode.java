package node.statement.pins;


import gen.Ardu3kParser;

public class PinToggleNode extends AbstractPinStmtNode {
    public PinToggleNode(Ardu3kParser.PinToggleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "TOGGLE"; }
}
