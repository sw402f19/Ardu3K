package node.expression.additive;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class MinusNode extends AbstractInfixAdditiveNode {

    @Override
    public String toString() {
        return  " - " ;
    }

}
