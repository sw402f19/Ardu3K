package visitor.semantic.typecast;

import exception.ErrorNode;
import exception.NotCastableException;
import node.RootNode;
import node.primary.IntegerNode;
import node.primary.RealNode;
import node.primary.StringNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TypeCaster {

    private static HashMap<Class, List<Class>> dispatch = new HashMap<>();
    static {
        dispatch.put(IntegerNode.class, List.of(RealNode.class, StringNode.class));
        dispatch.put(RealNode.class, List.of(RealNode.class, StringNode.class));
    }
    public static boolean canCast(RootNode source, RootNode target) {
        return dispatch.get(source.getClass()).contains(target.getClass());
    }
    public static RootNode cast(RootNode source, RootNode target) {
        return handle(source, target.getClass());
    }

    private static HashMap<Class, Handler> dispatch2 = new HashMap<>();
    static {
        dispatch2.put(IntegerNode.class, (n, c) -> handleInteger((IntegerNode) n,c));
        dispatch2.put(RealNode.class, (n, c) -> handleReal((RealNode)n, c));
    }
    // todo temp errornode
    private static RootNode handleInteger(IntegerNode node, Class clazz) {
        if(clazz.isInstance(StringNode.class))
            return new StringNode(node.value);
        else if(clazz.isInstance(RealNode.class))
            return new RealNode(node.value);
        else return new ErrorNode(clazz);
    }

    private static RootNode handleReal(RealNode node, Class clazz) {
        if(clazz.isInstance(StringNode.class))
            return new StringNode(node.value);
        else if(clazz.isInstance(RealNode.class))
            return new RealNode(node.value);
        else return new ErrorNode(clazz);
    }
    private static RootNode handle(Object o, Class target) {
        Handler h = dispatch2.get(o.getClass());
        if(h != null)
            return h.handle(o, target);
        return null;
    }
}
