package ASTVisitor.primary;

public class StringValNode extends AbstractPrimaryNode{
    public String value;

    public StringValNode(String value) {
        this.value = value;
    }
}
