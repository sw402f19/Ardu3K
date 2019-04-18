package visitor;

import node.RootNode;
import node.expression.DeclarationNode;
import symbol.*;

/* Sec 8.6.2 (p304) + 8.6.5 (p311) + 8.6.6 (p312) + 8.6.7 (p313) */
public class TypeVisitor extends TopDeclVisitor<RootNode>{

    /* === MOCK === */
    private SymbolTable st; // TODO: I'm not sure where we want to get the one we will use?
    private RootNode nodeAttributes; // TODO: How do we imagine these? As node types?
    /* ============ */

    // A template visitor for handeling type names
    public RootNode templateVisit(RootNode node){
        // Symbol attr = st.retrieveSymbol(node.name); // TODO: Node does currently not have a name
        if (attr != null && attr.getType() == nodeAttributes){
            // node.type = attr; // TODO: No type variable in RootNode
            // node.attributes = attr; // TODO: No attribute variable in nodes
        } else {
            //node.type = errorNode; // TODO: I assume we want to make our own version instead of using ANTLER's?
            // node.attributes = null; // TODO: No attribute variable in nodes
            throw new Exception("This identifier is not a type name: " + node.name);
        }
        return node;
    }

    @Override
    public RootNode visitDeclarationNode(DeclarationNode node) {
        return null;
    }
}
