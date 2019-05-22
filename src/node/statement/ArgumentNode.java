package node.statement;

import gen.Ardu3kParser;
import node.RootNode;

public class ArgumentNode extends RootNode {

    public ArgumentNode(Ardu3kParser.ArgumentContext ctx) {
        super(ctx);
    }

    public ArgumentNode() {
    }

    @Override
    public String toString() {
        return "Argument";
    }

}
