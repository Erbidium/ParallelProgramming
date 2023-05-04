package org.example;

public class MatrixFunctions {
    public static int[][] Multiply(int[][] matrixA, int[][] matrixB) {
        var result = new int[matrixA.length][matrixA[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    public static int[][] GetSubMatrix(int offset, int rows, int[][] matrix) {
        int columns = matrix[0].length;
        var subMatrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            subMatrix[i] = matrix[offset + i];
        }

        return subMatrix;
    }
}
