package org.example;

import java.util.HashMap;
import java.util.Map;

public class WordLengthCharacteristic {
    private double mean;

    private double variance;

    private double standardDeviation;


    public WordLengthCharacteristic(HashMap <Integer, Integer> frequenciesOfWordLengths)
    {
        mean = calculateMean(frequenciesOfWordLengths);
        variance = calculateVariance(frequenciesOfWordLengths, mean);
        standardDeviation = Math.sqrt(variance);
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }

    public double getStandardDeviation() {
        return standardDeviation;
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

    private static double calculateVariance(HashMap <Integer, Integer> frequenciesOfWordLengths, double mean)
    {

    }
}
