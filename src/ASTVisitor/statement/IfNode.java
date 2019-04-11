package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class IfNode extends AbstractStatementNode {

    /*@Override
    public String toString() {
        return "if "+condition.toString()+" do "+upperbody.toString();
    }*/

    public RootNode getCondition() {
        return children.get(0);
    }
    public void setCondition(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getUpperbody() {
        return children.get(1);
    }
    public void setUpperbody(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }
}
