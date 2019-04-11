package ASTVisitor.structure;

public abstract class AbstractASTVisitor implements ASTVisitor {
    public void visitChildren(RootNode node){

        if(node.children.size() > 0) {
            System.out.println();
            for (RootNode n : node.children)
                if (n != null) {
                    n.accept(this);
                    System.out.print("    ");
                }
            System.out.println();
        }
    }


}
