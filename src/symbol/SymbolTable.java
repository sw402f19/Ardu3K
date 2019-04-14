package symbol;

import node.RootNode;
import node.expression.AssignmentNode;
import node.structure.BlockNode;
import symbol.hashtable.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class SymbolTable {

    private static SymbolTable ourInstance = new SymbolTable();
    private static final HashMap<Class, Handler> dispatch = new HashMap<>();
    private static HashTable<Integer, Symbol> symTable = new HashTable<>();
    private static ArrayList<ArrayList<Symbol>> scopeDisplay = new ArrayList<>();
    private static int depth = 0;

    private SymbolTable() {
    }

    public void buildSymbolTable (RootNode root) {
        processNode(root);
    }
    private void processNode(RootNode node) {
        if(node != null) {
            handle(node);
            for (RootNode child : node.children)
                processNode(child);
        }

    }
    private static void openScope() {
        depth++;
        if(scopeDisplay.size() < depth)
            scopeDisplay.add(null);
        else
            scopeDisplay.set(depth, null);
    }
    static {
        dispatch.put(AssignmentNode.class, n ->
                handleAssignment((AssignmentNode) n));
        dispatch.put(BlockNode.class, n ->
                handleBlock((BlockNode) n));
    }

    private static void handleAssignment(AssignmentNode n) {
        symTable.add(n.hashCode(), new Symbol(n.getLeft(), n.getRight(), depth));
        symTable.add(n.hashCode(), new Symbol(n.getLeft(), n.getRight(), depth));
    }
    private static void handleBlock(BlockNode node) {
        openScope();
    }

    public static void handle(Object o) {
        Handler h = dispatch.get(o.getClass());
        if (h != null)
            h.handle(o);

    }
    public static SymbolTable getInstance() {
        return ourInstance;
    }


}
