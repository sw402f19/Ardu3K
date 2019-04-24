package node.statement;

import gen.Ardu3kParser;
import node.RootNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class ArgumentNode extends RootNode {

    public ArgumentNode(Ardu3kParser.ArgumentContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Argument";
    }

}
