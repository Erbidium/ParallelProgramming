package org.example;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        var foxMultiplier = new SequentialMatrixMultiplier();
        var matrixSizes = new int[] { 500, 1000, 1500, 2000, 2500 };
        var threadsCount = new int[] { 4, 8 };
        var foxBenchmark = new MatricesMultiplicationBenchmark(foxMultiplier, matrixSizes, threadsCount, 4);
        try {
            MatrixPrinter.Print(foxBenchmark.Run());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}