package org.example;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        var matrixGenerator = new MatrixGenerator();
        var sequentialMatrixMultiplier = new SequentialMatrixMultiplier();

        var matrixA = matrixGenerator.GenerateMatrixFilledWithValue(10, 1);
        var matrixB = matrixGenerator.GenerateMatrixFilledWithValue(10, 1);

        var stripped = new ParallelStrippedMatrixMultiplier(4);
        var result = stripped.Multiply(matrixA, matrixB);
        var matrix = result.getMatrix();
        //var result = sequentialMatrixMultiplier.Multiply(matrixA, matrixB);

        for (float[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }
}