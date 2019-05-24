package node.scope;

import gen.Ardu3kParser;
import node.RootNode;

import java.util.ArrayList;

public class ParameterNode extends RootNode {

    public ArrayList<RootNode> types = new ArrayList<>();

    public ParameterNode() {
    }

    public ParameterNode(Ardu3kParser.ParameterContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < children.size(); i++) {
            str += types.get(i).toString();
            str += " ";
            str += children.get(i).toString();
            if(!(children.size() == i - 1))
                str += ", ";
        }
        return str;
    }

}
