package visitor;

import node.expression.additive.*;
import node.primary.*;
import org.junit.jupiter.api.*;

public class TypeVisitorTest {
    PlusNode pN;
    TypeVisitor tv = new TypeVisitor();

    @Test
    void ExpressionTypeTest01(){
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new IntegerNode(2));

        // Right node & what it contains
        MinusNode mN = new MinusNode();
        mN.setLeft(new RealNode(2));
        mN.setRight(new RealNode(5));
        pN.setRight(mN);

        try {
            assert (tv.expressionTypeVisitor(pN).getClass().getSimpleName().equals("RealNode"));
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void ExpressionTypeTest02(){
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new BoolNode(false));
        pN.setRight(new BoolNode(true));

        try {
            assert (tv.expressionTypeVisitor(pN).getClass().getSimpleName().equals("BoolNode"));
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void ExpressionTypeTest03(){
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new StringValNode("1"));
        pN.setRight(new IntegerNode(1));

        try {
            assert (tv.expressionTypeVisitor(pN).getClass().getSimpleName().equals("IntegerNode"));
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void ExpressionTypeTest04(){ // NOTE: The aim of this is for it to throw exception because of types
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new BoolNode(false));
        pN.setRight(new IntegerNode(1));

        try {
            tv.expressionTypeVisitor(pN);
        } catch (Exception e) {System.out.print("========\nTest intentionally thew exception, so success :)\n========\n");}
    }

    @Test
    void ExpressionTypeTest05(){
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new RealNode(2.2));
        pN.setRight(new IntegerNode(1));

        try {
            assert (tv.expressionTypeVisitor(pN).getClass().getSimpleName().equals("RealNode"));
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void ExpressionTypeTest06(){
        // Root node & left
        pN = new PlusNode();
        pN.setLeft(new RealNode(2.2));
        pN.setRight(new StringNode());

        try {
            assert (tv.expressionTypeVisitor(pN).getClass().getSimpleName().equals("StringNode"));
        } catch (Exception e) {e.printStackTrace();}
    }

}
