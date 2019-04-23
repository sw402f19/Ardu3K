package node.expression.additive;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class PlusNode extends AbstractInfixAdditiveNode{

    @Override
    public String toString() {
        return  " + " ;
    }

}
