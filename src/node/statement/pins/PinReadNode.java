package node.statement.pins;

import gen.Ardu3kParser;

public class PinReadNode extends AbstractPinStmtNode {
    public PinReadNode(Ardu3kParser.PinReadContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "READ"; }
}