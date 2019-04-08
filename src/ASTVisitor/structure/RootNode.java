package ASTVisitor.structure;

import org.antlr.v4.runtime.RuleContext;

enum TYPE {INT, DOUBLE, STRING, TIME}

public abstract class RootNode implements Node {
    public RootNode() { }

    @Override
    public String toPrettyString() {
        return null;
    }
}
