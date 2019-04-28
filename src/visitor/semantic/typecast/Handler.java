package visitor.semantic.typecast;

import exception.IllegalTypeException;
import node.RootNode;

public interface Handler {
    RootNode handle(Object src, Class clazz) throws IllegalTypeException;
}
