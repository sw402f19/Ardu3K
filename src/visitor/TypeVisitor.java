package visitor;

import node.RootNode;
import node.expression.DeclarationNode;

public class TypeVisitor extends TopDeclVisitor<RootNode>{

    /* Sec 8.6.2 (p304) + 8.6.5 (p311) + 8.6.6 (p312) + 8.6.7 (p313) */


    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        return null;
    }
}
