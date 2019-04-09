package ASTVisitor.structure;

import ASTVisitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

enum TYPE {INT, DOUBLE, STRING, TIME}

public abstract class RootNode<T extends RuleContext> extends ASTVisitor implements Node {

    public RootNode parent;
    public ArrayList<RootNode> children = new ArrayList<>();


    T ctx;

    public RootNode(T ctx) {
        this.ctx = ctx;
    }

    public String toString(){
        return ctx.toString();
    }

    public RootNode(RootNode parent) {
        this.parent = parent;
    }

    @Override
    public String toPrettyString() {
        return null;
    }

    /**
     * Collects children from a given list of type T extending ParserRulerContext to
     * the this node's childen and adds this as parent to its children.
     * @param list to collect from
     * @param <T> type of list to collect from
     */
    public <T extends ParserRuleContext> void collectChildren(List<T> list) {
        if(!list.isEmpty())
            list.forEach(e -> children.add(super.visit(e)));
        for(RootNode n : children)
            if(n != null)
                n.parent = this;
    }
    /**
     * Collects children from a given list of type T extending ParserRuleContext to the supplied
     * list. Adds this node as parent.
     * @param source to collect from
     * @param target to collect to
     * @param <T> type of list to collect from
     */
    public <T extends ParserRuleContext> void collectChildren(List<T> source, ArrayList<RootNode> target) {
        if(!source.isEmpty())
            source.forEach(e -> target.add(super.visit(e)));
        for(RootNode n : target) {
            if (n != null)
                n.parent = this;
        }
    }

}
