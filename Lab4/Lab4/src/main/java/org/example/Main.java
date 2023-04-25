package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));
        FrequenciesCounter frequenciesCounter = new FrequenciesCounter();
        final int repeatCount = 4;
        long startTime, stopTime, averTime = 0;

        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            frequenciesOfWordLengths = frequenciesCounter.countFrequenciesInParallel(folder);
            stopTime = System.currentTimeMillis();
            averTime+=stopTime - startTime;
        }

        System.out.println(frequenciesOfWordLengths + " words are fined. Fork / join search took " + averTime/repeatCount+ "ms");

        averTime = 0;

        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            frequenciesOfWordLengths = frequenciesCounter.countFrequenciesOnSingleThread(folder);
            stopTime = System.currentTimeMillis();
            averTime+=stopTime - startTime;
        }

        System.out.println(frequenciesOfWordLengths + " words are fined. Single thread search took " + averTime/repeatCount+ "ms");
    }
}