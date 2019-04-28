package visitor.semantic.typecast;

import node.RootNode;

public interface Handler {
    RootNode handle(Object o, Class clazz);
}
