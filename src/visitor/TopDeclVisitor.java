package visitor;

import node.RootNode;
import symbol.*;

public class TopDeclVisitor extends SemanticsVisitor {

    /* === MOCK === */
    private SymbolTable st; // TODO: I'm not sure where we want to get the one we will use?
    private RootNode variableAttributes; // TODO: How do we imagine these? As node types?
    /* ============ */

   /* public RootNode templateVarDeclVisit(VariableListDeclaring vld){ // TODO: Figure out which class to handle vld
        TypeVisitor tv = new TypeVisitor();
        // Symbol attr = st.retrieveSymbol(node.name); // TODO: Node does currently not have a name
        // vld.typeName.accept(tv);
        for (RootNode id: vld){
            if (st.declaredLocally(id.name)){ // TODO: Make RootNode contain a name
                // id.type = errorNode; // TODO: I assume we want to make our own version instead of using ANTLER's?
                // id.attributes = null; // TODO: No attribute variable in nodes
                // throw new Exception("This variable is already declared " + id.name);
            } else {
                // id.type = vld.typeName.type; // TODO: A lot of missing variables :)
                // attr.kind = variableAttributes; // TODO: We need an attributes class
                // attr.variableType = id.type; // TODO: attr should contain variableType
                // id.attributesRef = attr // TODO: RootNode not containing attributes...
                // st.enterSymbol(id.name, attr); // TODO: Enter symbol should take two args
            }
        }
        // return vld; // TODO: Define what return should be
    }*/

}
