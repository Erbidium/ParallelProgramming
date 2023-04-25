package Task_4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));

        var keywords = new HashSet<>(Arrays.asList("project", "book", "light"));
        keywords.add("project");

        var parallelKeyWordsSearchTask = new ParallelKeyWordsSearchTask();
        var keywordsInDocs = parallelKeyWordsSearchTask.findKeywords(folder, keywords);

        for (var keywordsInDoc: keywordsInDocs.entrySet()) {
            System.out.println("Doc: " + keywordsInDoc.getKey());
            System.out.println("Found keywords: " + keywordsInDoc.getValue());
        }
    }
}