package visitor.codegen;

import exception.factory.SemanticException;
import node.RootNode;
import node.scope.ProgramNode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {

    public static CodeGenerator thisInstance;

    // The primary function for generating code
    public static void GenerateCode(String fileName, RootNode topNode) throws IOException, SemanticException, RuntimeException {
        if (topNode instanceof ProgramNode) {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName + ".c"));
            CodeGenVisitor genVisitor = new CodeGenVisitor();

            wr.write(genVisitor.visit((ProgramNode) topNode));

            wr.close();
        } else throw new RuntimeException("ERROR: Top node in CodeGen is not a ProgramNode");
    }
}
