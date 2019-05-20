package testVisitors;

import node.RootNode;
import node.scope.ProgramNode;

import static org.junit.jupiter.api.Assertions.fail;

public class BuilderTest {

    // The starting point of the tes
    public static void Test(RootNode ast){
        if (ast instanceof ProgramNode){
            testVisit((ProgramNode) ast);
        } else fail("FAIL: Test top node in builder test is not ProgramNode");
    }

    static void testVisit(ProgramNode node){
        System.out.println("I'm testing a programNode :)");
    }
}
