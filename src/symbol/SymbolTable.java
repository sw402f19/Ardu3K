package symbol;

import node.RootNode;
import node.expression.AssignmentNode;
import node.structure.BlockNode;
import node.structure.LoopNode;
import node.structure.SetupNode;
import symbol.hashtable.HashTable;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {

    private static SymbolTable ourInstance = new SymbolTable();
    private static final HashMap<Class, Handler> dispatch = new HashMap<>();
    private static HashTable<String, Symbol> symTable = new HashTable<>();
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
            if(node instanceof BlockNode)
                closeScope();
        }
    }
    private static void openScope() {
        depth++;
        if(scopeDisplay.size() <= depth)
            scopeDisplay.add(new ArrayList<>());
        else
            scopeDisplay.set(depth-1, null);
    }
    private static void closeScope() {
        Symbol prevSym;
        for(Symbol sym : scopeDisplay.get(depth-1)) {
            prevSym = symTable.getNext(sym.getName().toString());
            symTable.remove(sym.getName().toString());
            if(prevSym != null)
                symTable.add(prevSym.getName().toString(), prevSym);
        }
        depth--;
    }
    private static void enterSymbol(Symbol newSymbol) {
        Symbol oldsymbol = retrieveSymbol(newSymbol);
        if(oldsymbol != null && oldsymbol.getDepth() == depth)
            error("Duplicate identifier of name: "+newSymbol.getName());

        scopeDisplay.get(depth-1).add(newSymbol);
        symTable.add(newSymbol.getName().toString(), newSymbol);
    }

    private static Symbol retrieveSymbol(Symbol name) {
        Symbol symbol = symTable.get(name.getName().toString());
        while (symbol != null) {
            if (symbol.getName() == name.getName())
                return symbol;
            symbol = symbol.getHash();
        }
        return null;
    }

    static {
        dispatch.put(AssignmentNode.class, n ->
                handleAssignment((AssignmentNode) n));
        dispatch.put(BlockNode.class, n ->
                handleBlock((BlockNode) n));
        dispatch.put(SetupNode.class, n ->
                handleSetup((SetupNode) n));
        dispatch.put(LoopNode.class, n ->
                handleLoop((LoopNode) n));
    }

    private static void handleAssignment(AssignmentNode n) {
        enterSymbol(new Symbol(n.getLeft(), n.getRight(), depth));
    }
    private static void handleBlock(BlockNode node) {
        openScope();
    }
    private static void handleSetup(SetupNode node) {
        openScope();
    }
    private static void handleLoop(LoopNode node) {
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
    public static void error(String str) {
        System.out.println(str);
    }


}
