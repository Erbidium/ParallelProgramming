package org.example;

import java.util.HashMap;

public class SearchTaskBenchmark {
    public static long getExecutionTimeOfSearchTask(Folder folder, IFrequenciesSearchTask frequenciesSearchTask)
    {
        int repeatCount = 4;
        long startTime, stopTime, averageTime = 0;

        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();
        WordLengthCharacteristic wordLengthCharacteristic;

        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();

            frequenciesOfWordLengths = frequenciesSearchTask.countFrequencies(folder);
            wordLengthCharacteristic = new WordLengthCharacteristic(frequenciesOfWordLengths);

            stopTime = System.currentTimeMillis();
            averageTime += stopTime - startTime;
        }

        return averageTime / repeatCount;
    }
}
