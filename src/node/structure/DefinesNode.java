package node.structure;

import node.RootNode;
import org.antlr.v4.runtime.ParserRuleContext;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

public class DefinesNode extends RootNode {


    @Override
    public String toString() {
        return "Defines";
    }

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        if ( visitor instanceof BaseASTVisitor) return ((BaseASTVisitor<? extends T>)visitor).visitDefinesNode(this);
        else return visitor.visitChildren(this);
    }
}
