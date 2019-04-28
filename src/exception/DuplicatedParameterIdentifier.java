package exception;

import node.RootNode;
import node.scope.ParameterNode;

public class DuplicatedParameterIdentifier extends RuntimeException {

    public DuplicatedParameterIdentifier(RootNode node){
        super(node.getLine()+" Identifier \""+node.toString()+"\" duplicated");
    }
}
