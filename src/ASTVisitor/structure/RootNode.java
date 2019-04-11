package ASTVisitor.structure;

import ASTVisitor.CSTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;

enum TYPE {INT, DOUBLE, STRING, TIME}

@SuppressWarnings (value="unchecked")
public abstract class RootNode implements Node {

    public RootNode parent;
    public ArrayList<RootNode> children = new ArrayList<>();

    public RootNode(RootNode parent) {
        this.parent = parent;
    }

    public RootNode() {
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        if (visitor instanceof AbstractASTVisitor)
            return ((AbstractASTVisitor<T>)visitor).visitChildren(this);
        else
            return visitor.visitChildren(this);
    }
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

}
