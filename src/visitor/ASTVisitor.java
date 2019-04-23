package visitor;

import node.Node;
import node.RootNode;
import node.composite.ListNode;
import node.expression.*;
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

import java.util.ArrayList;

public interface ASTVisitor<T> {
    ArrayList<RootNode> children = new ArrayList<>();

    T visitChildren(RootNode node);

    T visit(Node node);
}
