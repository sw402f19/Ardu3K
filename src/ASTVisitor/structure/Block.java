package ASTVisitor.structure;

import java.util.ArrayList;
import java.util.List;

public class Block extends RootNode {
    List<RootNode> blockStmt = new ArrayList<>();

    public Block(List<RootNode> blockStmt) {
        this.blockStmt = blockStmt;
    }

    public Block() {

    }
}
