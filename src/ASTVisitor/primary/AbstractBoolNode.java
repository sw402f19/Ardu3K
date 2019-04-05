package ASTVisitor.primary;

import ASTVisitor.structure.RootNode;

public abstract class AbstractBoolNode extends RootNode {
    public boolean value;

    public AbstractBoolNode () { }

    public AbstractBoolNode(boolean value) {
        this.value = value;
    }
}
