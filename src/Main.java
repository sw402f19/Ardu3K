import ASTVisitor.structure.RootNode;
import ASTVisitor.ASTVisitor;
import gen.Ardu3kLexer;
import gen.Ardu3kParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CharStream is = CharStreams.fromFileName("test.txt");
        Ardu3kLexer lexer = new Ardu3kLexer(is);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Ardu3kParser parser = new Ardu3kParser(tokenStream);
        RootNode ast;

        try {

            Ardu3kParser.CompileUnitContext cst = parser.compileUnit();
            ast =  new ASTVisitor().visitCompileUnit(cst);
            System.out.println("Im here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}