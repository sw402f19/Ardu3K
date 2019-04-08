package ASTVisitor.structure;

import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;


/**
 * Use for all classes which rules calls itself
 * @param <T>
 */
public abstract class SelfRecursiveNode<T extends SelfRecursiveNode>
        extends RootNode implements SelfRecursiveInterface<T> {
    T field;
    public T newInstance(SelfRecursiveInterface<? extends T> factory) {
        return field = factory.newObject();
    }

    public SelfRecursiveNode(T field) {
        this.field = field;
    }

    public SelfRecursiveNode() {
    }

    public ArrayList<T> children = new ArrayList<>();
}
