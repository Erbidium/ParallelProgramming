package org.example;

public class MatrixFunctions {
    public static int[][] Multiply(int[][] matrixA, int[][] matrixB) {
        int matrixSize = matrixA.length;

        var result = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
}
