package Task_1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));

        /*var parallelFrequenciesSearchTask = new ParallelFrequenciesSearchTask();
        var forkJoinTime = SearchTaskBenchmark.getExecutionTimeOfSearchTask(folder, parallelFrequenciesSearchTask);
        System.out.println("Fork / join search took " + forkJoinTime + "ms");

        var singleThreadedFrequenciesSearchTask = new SingleThreadedFrequenciesSearchTask();
        var singleThreadedTime = SearchTaskBenchmark.getExecutionTimeOfSearchTask(folder, singleThreadedFrequenciesSearchTask);
        System.out.println("Single thread search took " + singleThreadedTime + "ms");*/

        var parallelFrequenciesSearchTask = new ParallelFrequenciesSearchTask();
        var frequenciesOfWordLengths = parallelFrequenciesSearchTask.countFrequencies(folder);
        var wordLengthCharacteristic = new WordLengthCharacteristic(frequenciesOfWordLengths);
        WordLengthCharacteristicPrinter.print(wordLengthCharacteristic);
    }
}