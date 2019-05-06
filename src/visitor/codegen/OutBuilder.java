package visitor.codegen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutBuilder {

    private static OutBuilder thisInstance = new OutBuilder();
    private StringBuilder stringBuilder = new StringBuilder();

    private OutBuilder() {

    }
    public void append(String str) {
        stringBuilder.append(str);
    }
    public void newLine() {
        stringBuilder.append("\n");
    }

    public void writeOut() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"))){
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OutBuilder getInstance() {
        return thisInstance;
    }
}
