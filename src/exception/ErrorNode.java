package exception;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class ErrorNode extends RootNode {

    Class errorClass;
    public ErrorNode(RootNode parent) {
        super(parent);
    }

    public ErrorNode(ParserRuleContext ctx) {
        super(ctx);
    }

    public ErrorNode(RootNode parent, ParserRuleContext ctx) {
        super(parent, ctx);
    }

    public ErrorNode() {
    }

    public ErrorNode(Class clazz) {
        errorClass = clazz;
    }
}
