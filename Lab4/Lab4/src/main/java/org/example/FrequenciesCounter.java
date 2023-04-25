package org.example;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class FrequenciesCounter {

    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static HashMap<Integer, Integer> calculateFrequenciesOfWorldLengths(Document document) {
        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                frequenciesOfWordLengths.compute
                (
                    word.length(),
                    (key, value) -> (value == null) ? 1 : value + 1
                );
            }
        }
        return frequenciesOfWordLengths;
    }
}


