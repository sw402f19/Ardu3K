package node.primary;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import static java.util.Objects.hash;

public class IdentifierNode extends AbstractPrimaryNode {
    String value;


    public IdentifierNode(Ardu3kParser.IdentifierContext ctx){
        value = ctx.value.getText();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return ((IdentifierNode) obj).value.equals(value);
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitIdentifierNode(this);
        else return visitor.visitChildren(this);
    }
}
