package ASTVisitor.primary;

import ASTVisitor.structure.RootNode;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Calendar;
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

    public StringNode(Ardu3kParser.StringContext ctx){
        super(ctx);
    }

    @Override
    public String toString() {
        return value;
    }


}
