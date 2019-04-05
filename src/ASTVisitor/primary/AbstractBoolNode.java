package ASTVisitor.primary;

import ASTVisitor.structure.BaseNode;

public abstract class AbstractBoolNode extends BaseNode {
    public boolean value;

    public AbstractBoolNode () { }

    public AbstractBoolNode(boolean value) {
        this.value = value;
    }
}
