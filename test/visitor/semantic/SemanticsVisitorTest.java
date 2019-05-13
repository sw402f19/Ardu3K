package visitor.semantic;

import exception.factory.SemanticException;
import exception.type.IllegalOperandException;
import node.expression.AbstractInfixExpressionNode;
import node.expression.additive.PlusNode;
import node.primary.AbstractNumberNode;
import node.primary.AbstractPrimaryNode;
import node.primary.BoolNode;
import node.primary.IntegerNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import symbol.SymbolTable;

import static org.junit.jupiter.api.Assertions.fail;

public class SemanticsVisitorTest {
    private SymbolTable symTbl;
    private SemanticsVisitor semVis;

    @BeforeEach
    void setup() {
        symTbl = new SymbolTable();
        semVis = new SemanticsVisitor(symTbl);
    }

    /* Abstract infix node */
    @Test
    void testAbstInfixNode01() {
        IntegerNode left = new IntegerNode(1);
        AbstractInfixExpressionNode node = new PlusNode();
        node.setLeft(left);
        assert (node.getLeft().getClass().getSimpleName().equals("IntegerNode"));
    }

    @Test
    void testAbstInfixNode02() {
        IntegerNode right = new IntegerNode(1);
        AbstractInfixExpressionNode node = new PlusNode();
        node.setRight(right);
        assert (node.getLeft().getClass().getSimpleName().equals("IntegerNode"));
    }

    /* Plus node */
    @Test
    void testPlusNode01() {
        AbstractNumberNode left = new IntegerNode(1);
        AbstractNumberNode right = new IntegerNode(1);
        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);
        try {
            semVis.visit(node);
        } catch (SemanticException e) { fail("Plus node does not accept two number"); }
    }

    @Test
    void testPlusNode02() {
        AbstractNumberNode left = new IntegerNode(1);
        BoolNode right = new BoolNode(false);
        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);
        try {
            semVis.visit(node);
            fail("PlusNode accepts bool");
        } catch (SemanticException e) { /* Supposed to be called */ }
    }

    @Test
    void testPlusNode03() {
        AbstractNumberNode leftL = new IntegerNode(1);
        AbstractNumberNode rightL = new IntegerNode(1);
        PlusNode left = new PlusNode();
        left.setLeft(leftL);
        left.setRight(rightL);

        AbstractNumberNode right = new IntegerNode(1);

        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);

        try {
            semVis.visit(node);
        } catch (SemanticException e) { fail("PlusNode with integer plus-expression failed"); }
    }

    @Test
    void testPlusNode04() {
        AbstractNumberNode left = new IntegerNode(1);

        AbstractNumberNode leftR = new IntegerNode(1);
        AbstractNumberNode rightR = new IntegerNode(1);
        PlusNode right = new PlusNode();
        right.setLeft(leftR);
        right.setRight(rightR);

        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);

        try {
            semVis.visit(node);
        } catch (SemanticException e) { fail("PlusNode with integer plus-expression failed"); }
    }

    @Test
    void testPlusNode05() {
        AbstractNumberNode left = new IntegerNode(1);

        AbstractNumberNode leftR = new IntegerNode(1);
        AbstractPrimaryNode rightR = new BoolNode(false);
        PlusNode right = new PlusNode();
        right.setLeft(leftR);
        right.setRight(rightR);

        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);

        try {
            semVis.visit(node);
            fail("Bool was allowed in plus expression of left side");
        } catch (SemanticException e) { /* Expected to call this */ }
    }

    @Test
    void testPlusNode06() {
        AbstractNumberNode leftL = new IntegerNode(1);
        AbstractPrimaryNode rightL = new BoolNode(false);
        PlusNode left = new PlusNode();
        left.setLeft(leftL);
        left.setRight(rightL);

        AbstractNumberNode right = new IntegerNode(1);

        PlusNode node = new PlusNode();
        node.setLeft(left);
        node.setRight(right);

        try {
            semVis.visit(node);fail("Bool was allowed in plus expression of left side");
        } catch (SemanticException e) { /* Expected to call this */ }
    }

}
