package ASTVisitor.structure;

import ASTVisitor.CSTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;

enum TYPE {INT, DOUBLE, STRING, TIME}

public abstract class RootNode implements Node {

    public RootNode parent;
    public ArrayList<RootNode> children = new ArrayList<>();

    public RootNode(RootNode parent) {
        this.parent = parent;
    }

    public RootNode() {
    }

    public <T> void accept(ASTVisitor<? extends T> visitor){
        if (visitor instanceof AbstractASTVisitor)
            ((AbstractASTVisitor)visitor).visitChildren(this);
        else
            visitor.visitChildren(this);
    }

}
