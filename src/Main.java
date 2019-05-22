import exception.factory.SemanticException;
import gen.Ardu3kLexer;
import gen.Ardu3kParser;
import node.RootNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sun.misc.Unsafe;
import symbol.SymbolTable;
import visitor.builder.BuildASTVisitor;
import visitor.codegen.CodeGenerator;
import visitor.semantic.SemanticsVisitor;

import java.io.IOException;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        disableWarning();
        CharStream is = CharStreams.fromFileName("test3.txt");
        Ardu3kLexer lexer = new Ardu3kLexer(is);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Ardu3kParser parser = new Ardu3kParser(tokenStream);
        RootNode ast;
        RootNode dast;

        try {
            Ardu3kParser.CompileUnitContext cst = parser.compileUnit();
            SymbolTable symbolTable = new SymbolTable();
            ast =  new BuildASTVisitor(symbolTable).visitCompileUnit(cst);
            dast = new SemanticsVisitor(symbolTable).visit(ast);
            CodeGenerator.GenerateCode("testGenCode", dast);
            PrintInfo(System.currentTimeMillis() - time);
        } catch (SemanticException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
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

    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}
