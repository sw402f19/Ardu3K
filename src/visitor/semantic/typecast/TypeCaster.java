package visitor.semantic.typecast;

import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import exception.type.IllegalTypeException;
import node.RootNode;
import node.primary.FloatNode;
import node.primary.IntegerNode;
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
    private static boolean canCast(RootNode source, RootNode target) throws SemanticException {
        boolean listCast = true;
        if(!castable.containsKey(source.getClass()))
            return false;
        return castable.get(source.getClass()).contains(target.getClass());
    }
    public static RootNode cast(RootNode source, RootNode target) throws SemanticException {
        if(source.getClass().equals(target.getClass()))
            return source;
        if (canCast(source, target))
            return handle(source, target.getClass());
        else throw ExceptionFactory.produce("notcastable", target, source);
    }

    private static HashMap<Class, Handler> dispatch = new HashMap<>();
    static {
        dispatch.put(IntegerNode.class, (n, c) -> handleInteger((IntegerNode) n,c));
        dispatch.put(FloatNode.class, (n, c) -> handleReal((FloatNode)n, c));
    }

    private static RootNode handleInteger(IntegerNode node, Class clazz) {
        if(clazz.equals(StringNode.class)) {
            System.out.println(node.getLine()+" casted int to string");
            return new StringNode(node);
        }
        else {
            System.out.println(node.getLine()+" casted int to float");
            return new FloatNode(node);
        }
    }

    private static RootNode handleReal(FloatNode node, Class clazz) {
        if(clazz.equals(StringNode.class)) {
            System.out.println(node.getLine()+" casted float to string");
            return new StringNode(node);
        }
        else {
            System.out.println(node.getLine()+" casted float to int");
            return new IntegerNode(node);
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
