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

        try {

            Ardu3kParser.ProgramUnitContext cst = parser.programUnit();
            System.out.println(cst.toString());
            //ProgramNode ast = cst.accept(new ASTVisitor());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}