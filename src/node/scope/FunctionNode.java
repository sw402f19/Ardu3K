package node.scope;

import exception.IllegalParameterTypeException;
import gen.Ardu3kParser;
import node.RootNode;
import node.expression.type.ExpressionType;
import node.primary.AbstractPrimaryNode;
import visitor.ASTVisitor;
import visitor.BaseASTVisitor;

import java.util.ArrayList;

public class FunctionNode extends RootNode {
    RootNode type;
    private ArrayList<RootNode> parameterTypes = new ArrayList<>();

    public FunctionNode(Ardu3kParser.FunctionContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "Function ";
    }

    public RootNode getId() {
        return children.get(0);
    }
    public void setId(RootNode node) {
        if(children.size() > 0)
            children.set(0, node);
        else
            children.add(node);
    }

    public RootNode getParameter() {
        return children.get(1);
    }
    public void setParameter(RootNode node) {
        if(children.size() > 1)
            children.set(1, node);
        else
            children.add(node);
    }

    public RootNode getBlock() {
        return children.get(2);
    }
    public void setBlock(RootNode node) {
        if(children.size() > 2)
            children.set(2, node);
        else
            children.add(node);
    }
    public void setReturnType(RootNode type) {
        this.type = type;
    }
    public RootNode getReturnType() {
        return this.type;
    }

    public void setParameterType(RootNode node) throws IllegalParameterTypeException {
        if (node instanceof AbstractPrimaryNode){
            parameterTypes.add(node);
        } else throw new IllegalParameterTypeException(node.line + " INVALID TYPE to be set as parameter in function");
    }
    public ArrayList<RootNode> getParameterTypes(){ return parameterTypes; }
}
