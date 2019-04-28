package node;


import org.antlr.v4.runtime.ParserRuleContext;
import java.util.ArrayList;

@SuppressWarnings (value="unchecked")
public abstract class RootNode implements Node {

    public RootNode parent;
    public ArrayList<RootNode> children = new ArrayList<>();
    public String line;

    public RootNode(RootNode parent) {
        this.parent = parent;
    }

    public RootNode(ParserRuleContext ctx) {
        this.line = "Line "+ctx.start.getLine()+": "+ctx.start.getCharPositionInLine();
    }

    public RootNode(RootNode parent, ParserRuleContext ctx) {
        this.parent = parent;
        this.line = "Line "+ctx.start.getLine()+": "+ctx.start.getCharPositionInLine();
    }

    public RootNode() { }

    public void print(int level) {
        for (int i = 1; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(toString());
        for (RootNode child : children) {
            if(child != null)
                child.print(level + 1);
        }
    }

    public String getLine() {
        return line;
    }
}
