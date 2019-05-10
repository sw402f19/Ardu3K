import exception.factory.SemanticException;
import node.RootNode;
import visitor.builder.BuildASTVisitor;
import gen.Ardu3kLexer;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.codegen.CodeGenerator;
import visitor.semantic.SemanticsVisitor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        CharStream is = CharStreams.fromFileName("test3.txt");
        Ardu3kLexer lexer = new Ardu3kLexer(is);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Ardu3kParser parser = new Ardu3kParser(tokenStream);
        RootNode ast;
        RootNode dast;

        try {
            Ardu3kParser.CompileUnitContext cst = parser.compileUnit();
            ast =  new BuildASTVisitor().visitCompileUnit(cst);
            dast = new SemanticsVisitor().visit(ast);
            CodeGenerator.GenerateCode("testGenCode", dast);
            PrintInfo(System.currentTimeMillis() - time);
        } /*catch (SemanticException e){
            System.out.println(e.getMessage());
        } */catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PrintInfo(long time){
        String line = "========================================";

        System.out.println(line);
        System.out.println("         ____    __            __");
        System.out.println("   /\\    |   \\  |  \\  |     |    \\  | /");
        System.out.println("  /  \\   |___|  |   | |     |  __/  |/");
        System.out.println(" /____\\  |   \\  |   | |     |    \\  |\\");
        System.out.println("/      \\ |    | |__/   \\___/   __/  | \\ ");
        System.out.println(line);
        System.out.println(" <<< Successful compile! Took " + time + "ms >>>");
        System.out.println(line);
        //ast.print(0);
    }
}
