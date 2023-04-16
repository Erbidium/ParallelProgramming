package org.example;

public class SequentialMatrixMultiplicator {
    public float[][] Multiply(float[][] matrixA, float[][] matrixB) {
        int rows = matrixA.length;
        int columns = matrixB[0].length;

        var result = new float[rows][columns];

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
