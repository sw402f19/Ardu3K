package typecheck;

import node.RootNode;
import node.expression.AbstractInfixExpressionNode;
import node.structure.BlockNode;

import java.util.HashMap;
import java.util.Hashtable;

public class SymbolTable {

    private static SymbolTable ourInstance = new SymbolTable();
    private static final HashMap<Class, Handler> dispatch = new HashMap<>();
    private static Hashtable<Integer, Symbol> symTable = new Hashtable<>();

    private SymbolTable() {
    }

    public void buildSymbolTable (RootNode root) {
        processNode(root);
    }
    private void processNode(RootNode node) {
        handle(node);
        for(RootNode child : node.children)
            processNode(child);
    }
    static {
        dispatch.put(RootNode.class, n ->
                handleRoot((RootNode) n));
        dispatch.put(AbstractInfixExpressionNode.class, n ->
                handleAbstractInfixExpression((AbstractInfixExpressionNode) n));
        dispatch.put(BlockNode.class, n ->
                handleBlock((BlockNode) n));
    }

    private static void handleAbstractInfixExpression(AbstractInfixExpressionNode node) {

    }
    private static void handleRoot(RootNode node) {
    }
    private static void handleBlock(BlockNode node) {

    }

    public static void handle(Object o) {
        Handler h = dispatch.get(o.getClass());
        if (h == null)
            throw new IllegalArgumentException("Unknown node type");
        h.handle(o);
    }
    public static SymbolTable getInstance() {
        return ourInstance;
    }


}
