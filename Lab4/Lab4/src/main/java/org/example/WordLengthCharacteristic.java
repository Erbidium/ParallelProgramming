package org.example;

import java.util.HashMap;
import java.util.Map;

public class WordLengthCharacteristic {
    private double mean;


    public WordLengthCharacteristic(HashMap <Integer, Integer> frequenciesOfWordLengths)
    {
        mean = calculateMean(frequenciesOfWordLengths);
    }

    public double getMean() {
        return mean;
    }

    private static double calculateMean(HashMap <Integer, Integer> frequenciesOfWordLengths)
    {
        double sum = 0;
        int totalWordsCount = 0;
        for (Map.Entry<Integer,Integer> el: frequenciesOfWordLengths.entrySet()) {
            sum += (el.getKey() * el.getValue());
            totalWordsCount +=el.getValue();
        }

        return sum / totalWordsCount;
    }
}
