package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:/TextFolder"));
        WordCounter wordCounter = new WordCounter();
        String searchedWord = "synchronized";
        final int repeatCount = 4;
        long startTime, stopTime, counts=0, averTime = 0;

        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = wordCounter.countOccurrencesInParallel(folder, searchedWord);
            stopTime = System.currentTimeMillis();
            averTime+=stopTime - startTime;
        }

        System.out.println(counts + " words are fined. Fork / join search took " + averTime/repeatCount+ "ms");

        averTime = 0;

        for (int i = 0; i < repeatCount; i++) {
            startTime = System.currentTimeMillis();
            counts = wordCounter.countOccurrencesOnSingleThread(folder,searchedWord);
            stopTime = System.currentTimeMillis();
            averTime+=stopTime - startTime;
        }

        System.out.println(counts + " words are fined. Single thread search took " + averTime/repeatCount+ "ms");
    }
}