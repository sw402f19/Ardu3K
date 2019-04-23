import node.RootNode;
import visitor.BuildASTVisitor;
import gen.Ardu3kLexer;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.SemanticsVisitor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CharStream is = CharStreams.fromFileName("test.txt");
        Ardu3kLexer lexer = new Ardu3kLexer(is);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Ardu3kParser parser = new Ardu3kParser(tokenStream);
        RootNode ast;
        RootNode dast;

        try {

            Ardu3kParser.CompileUnitContext cst = parser.compileUnit();
            ast =  new BuildASTVisitor().visitCompileUnit(cst);
            dast = new SemanticsVisitor().visit(ast);
            System.out.println("==============\nSuccessful :)\n==============\n");
            //ast.print(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}