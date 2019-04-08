package ASTVisitor.structure;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends RootNode {
    public List<RootNode> blockStmt = new ArrayList<>();

    public BlockNode(List<RootNode> blockStmt) {
        this.blockStmt = blockStmt;
    }

    public BlockNode() {

    }
}
