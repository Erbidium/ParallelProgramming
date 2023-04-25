package Task_3;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));

        var parallelCommonWordsSearchTask = new ParallelCommonWordsSearchTask();
        var commonWords = parallelCommonWordsSearchTask.findCommonWords(folder);

        System.out.println("Number of common words: " + commonWords.size());
        System.out.println("Common words: " + commonWords);
    }
}