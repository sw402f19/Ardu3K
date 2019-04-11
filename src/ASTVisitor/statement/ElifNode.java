package ASTVisitor.statement;

import ASTVisitor.structure.RootNode;

public class ElifNode extends IfNode {

   /* @Override
    public String toString() {
        return "if "+condition.toString()+" do "+upperbody.toString()+" else "+lowerbody.toString();
    }*/

    public RootNode getLowerbody() {
        return children.get(0);
    }
    public void setLowerbody(RootNode lowerbody) {
        children.set(0, lowerbody);
    }

}
