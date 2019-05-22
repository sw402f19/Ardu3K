package symbol;

import node.scope.FunctionNode;

import java.util.ArrayList;

public class ImplSingleton {

    public static ArrayList<FunctionNode> impls = new ArrayList<>();
    private static ImplSingleton ourInstance = new ImplSingleton();

    public static ImplSingleton getInstance() {
        return ourInstance;
    }

    private ImplSingleton() {
    }
}
