package visitor;

import exception.IgnorableInvocationException;
import exception.factory.ExceptionFactory;
import exception.factory.NoProductException;
import exception.factory.SemanticException;
import node.Node;
import node.RootNode;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseASTVisitor<T> implements ASTVisitor<T> {

    // todo Exceptions should be thrown here, and handled individually after the visit.
    public T visit(Node node) throws SemanticException {
        try {
            return this.dispatch(node);
        } catch (NullPointerException | NoProductException n) {
            n.printStackTrace();
        } catch (IgnorableInvocationException e){
            return null;
        } catch (Throwable t) {
            throw ExceptionFactory.produce(t);
            //System.out.println(t.getMessage());
        }
        return null;
    }

    public T visitChildren(RootNode node) throws SemanticException {

        T dast = null;

        if(node.children.size() > 0) {
            for (RootNode n : node.children)
                if (n != null) {
                    dast = visit(n);
                }
        }
        return dast;
    }
    public T dispatch(Node node) throws Throwable {
        try {
            Method m = findMethod(node);
            Object o = m.invoke(this, node);
            if(o != null)
                return (T) o;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (NoSuchMethodException m) {
            return visitChildren((RootNode) node);
        }
        return null;
    }

    public Method findMethod(Node n) throws NoSuchMethodException {
        String methodName = "visit";
        Class node = n.getClass();
        while (isAncestorOf("node.Node", node)) {
            Class visitor = getClass();
            while (isAncestorOf("visitor.BaseASTVisitor", visitor)) {
                try {
                    Method method = visitor.getDeclaredMethod(methodName,
                            node);
                    return method;
                } catch (NoSuchMethodException e) {
                    visitor = visitor.getSuperclass();
                }
            }
            node = node.getSuperclass();
        }
        throw new NoSuchMethodException("No declared accept method for any subclass");
    }
    public boolean isAncestorOf(String ancestorName, Class descendant) {
        try{
            return Class.forName(ancestorName).isAssignableFrom(descendant);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
