package node.expression.multiplicative;

import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class TimesNode extends AbstractInfixMultiplicativeNode {

    @Override
    public String toString() {
        return "*";
    }

}
