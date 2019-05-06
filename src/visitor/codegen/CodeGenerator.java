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
    public static void GenerateCode(String fileName, RootNode topNode) throws IOException, SemanticException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(fileName + ".c"));
        CodeGenVisitor genVisitor = new CodeGenVisitor();

        if (topNode instanceof ProgramNode) { wr.write(genVisitor.visit((ProgramNode) topNode)); }

        wr.close();
    }
}
