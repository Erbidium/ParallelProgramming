package org.example;

public class Main {
    public static void main(String[] args) {
        var matrixMultiplier = new ParallelFoxMatrixMultiplier();

        /*
        var matrixSizes = new int[] { 500, 1000, 1500, 2000, 2500 };
        var threadsCount = new int[] { 2, 4, 8 };
        var foxBenchmark = new MatricesMultiplicationBenchmark(matrixMultiplier, matrixSizes, threadsCount, 4);
        try {
            MatrixPrinter.Print(foxBenchmark.Run());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        */

        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(100, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(100, 1);
        var result = matrixMultiplier.Multiply(matrixA, matrixB);
        MatrixPrinter.Print(result.getMatrix());

    }
}