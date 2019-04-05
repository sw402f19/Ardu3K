package ASTVisitor.structure;

import java.util.ArrayList;
import java.util.List;

public class Block extends BaseNode {
    public List<BaseNode> blockStmt = new ArrayList<>();

    public Block(List<BaseNode> blockStmt) {
        this.blockStmt = blockStmt;
    }

    public Block() {

    }
}
