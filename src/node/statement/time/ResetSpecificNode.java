package node.statement.time;

import gen.Ardu3kParser;
import node.RootNode;

public class ResetSpecificNode extends ResetNode {
    public ResetSpecificNode(Ardu3kParser.ResetSpecificContext ctx) { super(ctx); }

    public void setID(RootNode id){
        if (children.size() > 0){
            children.set(0, id);
        } else children.add(id);
    }
    public RootNode getID(){
        if (children.size() > 0) {
            return children.get(0);
        } else return null;
    }
}
