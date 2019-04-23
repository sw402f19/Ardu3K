package visitor;

import node.Node;
import node.RootNode;
import node.composite.ListNode;
import node.expression.AbstractExpressionNode;
import node.expression.AbstractInfixExpressionNode;
import node.expression.AssignmentNode;
import node.expression.DeclarationNode;
import node.expression.additive.AbstractInfixAdditiveNode;
import node.expression.additive.MinusNode;
import node.expression.additive.PlusNode;
import node.expression.condition.*;
import node.expression.multiplicative.*;
import node.expression.relation.*;
import node.expression.unary.UnaryNode;
import node.primary.*;
import node.statement.*;
import node.scope.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseASTVisitor<T> implements ASTVisitor<T> {

    public T visit(Node node) {
        try {
            return this.dispatch(node);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return null;
    }

    public T visitChildren(RootNode node){

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
        return visitChildren((RootNode) node);
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
            if(node.equals(RootNode.class))
                throw new NoSuchMethodException();
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
