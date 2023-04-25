package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));

        var parallelFrequenciesSearchTask = new ParallelFrequenciesSearchTask();
        var forkJoinTime = SearchTaskBenchmark.getExecutionTimeOfSearchTask(folder, parallelFrequenciesSearchTask);
        System.out.println("Fork / join search took " + forkJoinTime + "ms");

        var singleThreadedFrequenciesSearchTask = new SingleThreadedFrequenciesSearchTask();
        var singleThreadedTime = SearchTaskBenchmark.getExecutionTimeOfSearchTask(folder, singleThreadedFrequenciesSearchTask);
        System.out.println("Single thread search took " + singleThreadedTime + "ms");
    }
}