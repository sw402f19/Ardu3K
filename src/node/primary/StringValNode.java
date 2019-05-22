package node.primary;

public class StringValNode extends AbstractPrimaryNode{
    public String value;

    public StringValNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
