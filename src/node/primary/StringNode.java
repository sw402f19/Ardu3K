package node.primary;

import gen.Ardu3kParser;

import java.util.List;

public class StringNode extends AbstractPrimaryNode {
    public String value;

    public StringNode() {
    }
    public StringNode(String str) {
        value = str;
    }
    public StringNode(List<Ardu3kParser.String_valContext> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(e -> builder.append(e.getText()));
        value = builder.toString();
    }



    @Override
    public String toString() {
        return value;
    }


}
