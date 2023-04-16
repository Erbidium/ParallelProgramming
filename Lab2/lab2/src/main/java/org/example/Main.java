package org.example;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var matrixGenerator = new MatrixGenerator();
        var sequentialMatrixMultiplier = new SequentialMatrixMultiplier();

        var matrixA = matrixGenerator.GenerateMatrixFilledWithValue(10, 1);
        var matrixB = matrixGenerator.GenerateMatrixFilledWithValue(10, 1);

        var striped = new ParalellStrippedMatrixMultiplier();
        var result = striped.Multiply(matrixA, matrixB);
        //var result = sequentialMatrixMultiplier.Multiply(matrixA, matrixB);

        for (float[] row : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }
}