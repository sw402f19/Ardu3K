import gen.Ardu3kLexer;
import gen.Ardu3kParser;
import node.RootNode;
import node.primary.IntegerNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.*;
import symbol.SymbolTable;
import testVisitors.BuilderTest;
import visitor.builder.BuildASTVisitor;

import static org.junit.jupiter.api.Assertions.fail;

// =================================================================
// TEST METHODOLOGY:
// 1. Have a file with every function
// 2. Generate AST using ANTLER and buildVisitor
// 3. Ensure that the AST has been built correctly
// 4. Generate DAST
// 5. Ensure that the Semantic checks works
//    (might need separate file which fails semCheck on purpose)
// 6. Generate code file
// 7. Ensure the code was generated correctly
// =================================================================

public class MainTest {
    private String testFileName = "./test/TestArdu3KCode.Ardu3K";

    @Test
    void MainTest(){
        try {
            // Setup:
            Main.disableWarning();
            CharStream is = CharStreams.fromFileName(testFileName);
            Ardu3kLexer lexer = new Ardu3kLexer(is);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            Ardu3kParser parser = new Ardu3kParser(tokenStream);

            // Builder test:
            RootNode ast;
            Ardu3kParser.CompileUnitContext cst = parser.compileUnit();
            SymbolTable symbolTable = new SymbolTable();
            ast =  new BuildASTVisitor(symbolTable).visitCompileUnit(cst);
            BuilderTest.Test(ast);

            // Semantic check:
            //RootNode dast;
            //dast = new SemanticsVisitor(symbolTable).visit(ast);

            // Code gen check:
            //CodeGenerator.GenerateCode("testGenCode", dast);
        } /*catch (SemanticException e){
            System.out.println(e.getMessage());
        } */catch (Exception e) {
            fail("Exception called:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
