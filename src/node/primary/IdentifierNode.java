package node.primary;

import gen.Ardu3kParser;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

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
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitIdentifierNode(this);
        else return visitor.visitChildren(this);
    }
}
