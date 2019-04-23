package node.scope;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DefineNode extends RootNode {


    public DefineNode(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Define";
    }
    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode id) {
        if(children.size() > 0)
            children.set(0, id);
        else
            children.add(id);
    }
    public RootNode getValue() {
        return children.get(1);
    }
    public void setValue(RootNode value) {
        if(children.size() > 1)
            children.set(1, value);
        else
            children.add(value);
    }
}
