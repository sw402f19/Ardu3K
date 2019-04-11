package node.primary;

public class FalseNode extends AbstractBoolNode {


    public FalseNode(boolean value) {
        super(value);
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
