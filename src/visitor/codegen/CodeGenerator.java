package visitor.codegen;

import exception.factory.ExceptionFactory;
import node.RootNode;
import node.scope.ProgramNode;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeGenerator {

    // The primary function for generating code
    public static void GenerateCode(String fileName, RootNode topNode) throws Exception {
        if (topNode instanceof ProgramNode) {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName + ".c"));
            CodeGenVisitor genVisitor = new CodeGenVisitor();

            wr.write(genVisitor.visit((ProgramNode) topNode));

            wr.close();
        } else throw ExceptionFactory.produce("INVALIDTOPNODE", topNode);
    }
}
