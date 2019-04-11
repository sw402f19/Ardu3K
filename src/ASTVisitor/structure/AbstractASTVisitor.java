package ASTVisitor.structure;

public abstract class AbstractASTVisitor implements ASTVisitor {
    public void visitChildren(RootNode node){
       node.children.forEach(e -> e.accept(this));
    }


}
