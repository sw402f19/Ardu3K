package visitor.semantic.typecast;

import exception.type.IllegalTypeException;
import exception.factory.ExceptionFactory;
import exception.factory.SemanticException;
import node.RootNode;
import node.composite.ListNode;
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
    private static boolean canCast(RootNode source, RootNode target) throws SemanticException {
        boolean listCast = true;
        if(source instanceof ListNode && target instanceof ListNode) {
            for (int i = 0; i < source.children.size(); i++)
                listCast &= canCast(source.children.get(i), target.children.get(i));
            return listCast;
        } else if(!castable.containsKey(source.getClass()))
            return false;
        return castable.get(source.getClass()).contains(target.getClass());
    }
    public static RootNode cast(RootNode source, RootNode target) throws SemanticException {
        if (canCast(source, target))
            return handle(source, target.getClass());
        else throw ExceptionFactory.produce("notcastable", target, source);
    }

    private static HashMap<Class, Handler> dispatch = new HashMap<>();
    static {
        dispatch.put(IntegerNode.class, (n, c) -> handleInteger((IntegerNode) n,c));
        dispatch.put(FloatNode.class, (n, c) -> handleReal((FloatNode)n, c));
        dispatch.put(ListNode.class, (n, c) -> handleList((ListNode) n, c));
    }
    // todo temp errornode
    private static RootNode handleInteger(IntegerNode node, Class clazz) {
        if(clazz.equals(StringNode.class)) {
            System.out.println(node.getLine()+" casted int to string");
            return new StringNode(node.value);
        }
        else {
            System.out.println(node.getLine()+" casted int to float");
            return new FloatNode(node.value);
        }
    }

    private static RootNode handleReal(FloatNode node, Class clazz) {
        if(clazz.equals(StringNode.class)) {
            System.out.println(node.getLine()+" casted float to string");
            return new StringNode(node.value);
        }
        else {
            System.out.println(node.getLine()+" casted float to int");
            return new IntegerNode(node.value);
        }
    }
    private static RootNode handleList(ListNode node, Class clazz) throws IllegalTypeException {
        for(RootNode n : node.children) {
            System.out.println(node.getLine()+ " casted list element to "+clazz.getSimpleName());
            node.children.set(node.children.indexOf(n), handle(n, clazz));
        }
        return node;
    }
    private static RootNode handle(Object o, Class target) throws IllegalTypeException {
        Handler h = dispatch.get(o.getClass());
        if (h != null)
            return h.handle(o, target);
        else
            throw new IllegalTypeException();

    }
}
