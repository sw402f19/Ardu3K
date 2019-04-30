package node.primary;

import gen.Ardu3kParser;
import node.scope.FunctionNode;

public class TypedFuncIdentifierNode extends IdentifierNode {
    private FunctionNode functionNodeReference;

    public TypedFuncIdentifierNode() {}

    // Default implementation
    public TypedFuncIdentifierNode(Ardu3kParser.IdentifierContext ctx) {
        super(ctx);
        value = ctx.value.getText();
    }

    // Given a function node
    public TypedFuncIdentifierNode(Ardu3kParser.IdentifierContext ctx, FunctionNode node){
        super(ctx);
        value = ctx.value.getText() + "_" + node.getParameterString();
    }
}
