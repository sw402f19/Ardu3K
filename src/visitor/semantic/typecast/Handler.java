package visitor.semantic.typecast;

import exception.type.IllegalTypeException;
import node.RootNode;

public interface Handler {
    RootNode handle(Object src, Class clazz) throws IllegalTypeException;
}
