package node.primary;

public class CommentNode extends AbstractPrimaryNode {
    public String value;

    public CommentNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Comment";
    }

    public String getValue() {
        return value;
    }
}
