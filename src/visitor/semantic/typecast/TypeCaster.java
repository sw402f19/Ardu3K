package visitor.semantic.typecast;

import exception.IllegalTypeException;
import node.RootNode;
import node.primary.IntegerNode;
import node.primary.FloatNode;
import node.primary.StringNode;

import java.util.HashMap;
import java.util.List;

public class TypeCaster {

    private static HashMap<Class, List<Class>> castable = new HashMap<>();
    static {
        castable.put(IntegerNode.class, List.of(FloatNode.class, StringNode.class));
        castable.put(FloatNode.class, List.of(IntegerNode.class, StringNode.class));
        castable.put(StringNode.class, List.of());
    }
    private static boolean canCast(RootNode source, RootNode target) {
        return castable.get(source.getClass()).contains(target.getClass());
    }
    public static RootNode cast(RootNode source, RootNode target) throws IllegalTypeException {
        if (canCast(source, target))
            return handle(source, target.getClass());
        else throw new IllegalTypeException(source.getLine()+" Incompatible types, cannot cast "+source.toString()+
                    " to "+target.toString());
    }

    private static HashMap<Class, Handler> dispatch = new HashMap<>();
    static {
        dispatch.put(IntegerNode.class, (n, c) -> handleInteger((IntegerNode) n,c));
        dispatch.put(FloatNode.class, (n, c) -> handleReal((FloatNode)n, c));
    }
    // todo temp errornode
    private static RootNode handleInteger(IntegerNode node, Class clazz) {
        if(clazz.isInstance(StringNode.class)) {
            System.out.println(node.getLine()+" casted int to string");
            return new StringNode(node.value);
        }
        else {
            System.out.println(node.getLine()+" casted int to float");
            return new FloatNode(node.value);
        }
    }

    private static RootNode handleReal(FloatNode node, Class clazz) {
        if(clazz.isInstance(StringNode.class)) {
            System.out.println(node.getLine()+" casted float to string");
            return new StringNode(node.value);
        }
        else {
            System.out.println(node.getLine()+" casted float to int");
            return new IntegerNode(node.value);
        }
    }
    private static RootNode handle(Object o, Class target) throws IllegalTypeException {
        Handler h = dispatch.get(o.getClass());
        if (h != null)
            return h.handle(o, target);
        else
            throw new IllegalTypeException();

    }
}
