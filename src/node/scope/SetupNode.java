package node.scope;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class SetupNode extends RootNode {

    public SetupNode(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() { return "Setup"; }

    public BlockNode getBlock(){ return (BlockNode)children.get(0); }

    public void setBlock(RootNode n){
        if(!(n instanceof BlockNode))
            throw new IllegalArgumentException("Input node not of type BlockNode");
        if (children.size() != 0) {
            children.set(0, n);
        } else {
            children.add(n);
        }
    }
}
